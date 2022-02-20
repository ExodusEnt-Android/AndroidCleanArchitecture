package org.techtown.presentation

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.techtown.presentation.databinding.ActivitySplashBinding
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.model.UserRootModel
import org.techtown.presentation.retorfit.RetrofitBuilder
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    //통신 성공 여부.
    private var isSuccess:Boolean = false

    //타이머 여러개돌면 안되므로 한번만 초기화하게 해줌.
    private var countResponse : Boolean = false

    //처음 들어갈떄 임의용으로 정한 쿼리.
    private var firstQuery = "hello"

    //os gc가발동할떄 프로세스가 죽어버리니까 single객체 가로채야됨.
    private var disposable = CompositeDisposable()

    //유저 리스트.
    private var userList: ArrayList<UserModel>? = null

    //타이머 저장(화면 Destroy될때 없애주기위함).
    private lateinit var timerDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이머 시작.
        timerDisposable = excuteTimer()
    }

    private fun excuteTimer() = Observable.interval(1, 1, TimeUnit.SECONDS)
        .take(7).subscribe{ count ->

            val countOfvalue = count.toInt()
            runOnUiThread {
                binding.tvCount.text = "$countOfvalue"
            }

            //처음 들어갔을때 유저 목록 가져오기.
            if(countOfvalue == 0){
                runOnUiThread {
                    getFirstUserInfo()
                }
            }

            if (isSuccess && userList != null) { //0일될떄는 3초가 다 지났으므로 다음화면으로 넘어가줍니다(최소 2초일때 넘어가지게하기).
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                intent.putParcelableArrayListExtra("user_list", userList)
                intent.putExtra("first_query", firstQuery)
                startActivity(intent)
                finish()
            } else if (countOfvalue % 3 == 0 && countOfvalue >= 3 && countOfvalue < 6 && !isSuccess) { //3초마다 재연결을 해줘야 되므로.
                runOnUiThread { //현재 백그라운드라서 progress때문에 UiThread에서 돌게해줌(어차피 retrofit에서 백그라운드라 도니까 상관없음).
                    getFirstUserInfo()
                }
            } else if (countOfvalue % 3 == 0 && countOfvalue == 6 && !isSuccess) { //마지막까지 통신 안될시에는 사용자에게 알려주기.
                runOnUiThread {
                    binding.tvCount.text = "카운트종료"
                    getFirstUserInfo()
                    Toast.makeText(this@SplashActivity, "데이터 통신 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        }

    override fun onDestroy() {
        super.onDestroy()

        //프로세스 종료시 통신작업 중단.
        disposable.clear()

        //타이머도 중단.
        timerDisposable.dispose()

    }

    private fun getFirstUserInfo() {
        runOnUiThread {
            Util.showProgress(this@SplashActivity)
        }
        disposable.add(RetrofitBuilder.api.getUserInfo(firstQuery, Const.START_PAGE, Const.PER_PAGE_LIST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Util.closeProgress()
                userList = it!!.items
                isSuccess = true
            },{
                Util.closeProgress()
                isSuccess = false
                countResponse = true
            })
        )
    }
}