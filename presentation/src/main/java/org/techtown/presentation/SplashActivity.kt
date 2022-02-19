package org.techtown.presentation

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.techtown.presentation.databinding.ActivitySplashBinding
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.model.UserRootModel
import org.techtown.presentation.retorfit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    //3초 카운트다운 세기위해서 추가.
    private var count = 9
    private var countDownTimer:CountDownTimer? = null

    //통신 성공 여부.
    private var isSuccess:Boolean = false

    //타이머 여러개돌면 안되므로 한번만 초기화하게 해줌.
    private var countResponse : Boolean = false

    //프로그래스바.
    private var progressDialog: ProgressDialog? = null

    //처음 들어갈떄 임의용으로 정한 쿼리.
    private var firstQuery = "hello"

    //os gc가발동할떄 프로세스가 죽어버리니까 single객체 가로채야됨.
    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //처음 들어갔을때 유저 목록 가져오기.
        getFirstUserInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        //액티비티 없어지면 타이머도 취소 및 없애줌.
        try{
            countDownTimer!!.cancel()
        }catch (e: Exception){
            e.printStackTrace()
        }

        countDownTimer = null

        //프로세스 종료시 통신작업 중단.
        disposable.clear()

    }

    private fun countDownTimer(userList: ArrayList<UserModel>?) {
        countDownTimer = object : CountDownTimer(9000L, 1000L){
            override fun onTick(p0: Long) {
                binding.tvCount.text = "$count"
                count --
                if(count <= 8 && isSuccess){ //0일될떄는 3초가 다 지났으므로 다음화면으로 넘어가줍니다(최소 2초일때 넘어가지게하기).
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    intent.putParcelableArrayListExtra("user_list", userList)
                    intent.putExtra("first_query", firstQuery)
                    startActivity(intent)
                    finish()
                    countDownTimer!!.cancel()
                    countDownTimer = null
                } else if(count % 3 == 1 && count <= 7 && !isSuccess && count != 1) { //3초마다 재연결을 해줘야 되므로.
                    getFirstUserInfo()
                } else if(count % 3 == 1 && count == 1 && !isSuccess){ //마지막까지 통신 안될시에는 사용자에게 알려주기.
                    binding.tvCount.text = "카운트종료"
                    countDownTimer!!.cancel()
                    countDownTimer = null
                    Toast.makeText(this@SplashActivity, "데이터 통신 실패하였습니다." , Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFinish() {
                binding.tvCount.text = "카운트종료"
            }

        }
    }

    private fun getFirstUserInfo() {
        Util.showProgress(this@SplashActivity)
        disposable.add(RetrofitBuilder.api.getUserInfo(firstQuery, Const.START_PAGE, Const.PER_PAGE_LIST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Util.closeProgress()
                val userList = it!!.items

                if (!countResponse) {
                    countDownTimer(userList)
                }
                countDownTimer!!.start()

                isSuccess = true
                countResponse = true
            },{
                Util.closeProgress()
                if(!countResponse){
                    countDownTimer(null)
                    countDownTimer!!.start()
                }
                isSuccess = false
                countResponse = true
            })
        )
    }
}