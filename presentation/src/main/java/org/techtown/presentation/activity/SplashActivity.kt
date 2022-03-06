package org.techtown.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import org.techtown.presentation.util.Const
import org.techtown.presentation.util.Util
import org.techtown.presentation.databinding.ActivitySplashBinding
import org.techtown.presentation.datasource.local.LocalDataSourceImpl
import org.techtown.presentation.datasource.remote.RemoteDataSourceImpl
import org.techtown.presentation.db.UserDatabase
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.repository.UserRepository
import org.techtown.presentation.repository.UserRepositoryImpl
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
    private var compositeDisposable = CompositeDisposable()

    //유저 리스트.
    private var userList: ArrayList<UserModel>? = null

    //repository setting
    private val userRepository: UserRepository by lazy {
        //remote 데이터 세팅.
        val remoteDataSource = RemoteDataSourceImpl(api = RetrofitBuilder.api)
        val localDataSource = LocalDataSourceImpl(userDatabase = UserDatabase.getInstance(this))
        UserRepositoryImpl(remoteDataSource, localDataSource)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이머 시작.
        excuteTimer()
    }

    private fun excuteTimer() = Observable.interval(1, 1, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .take(7).subscribe { count ->

            val countOfvalue = count.toInt()
            binding.tvCount.text = "$countOfvalue"

            //처음 들어갔을때 유저 목록 가져오기.
            if (countOfvalue == 0) {
                getFirstUserInfo()
            }

            if (isSuccess && userList != null) { //0일될떄는 3초가 다 지났으므로 다음화면으로 넘어가줍니다(최소 2초일때 넘어가지게하기).
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                intent.putParcelableArrayListExtra("user_list", userList)
                intent.putExtra("first_query", firstQuery)
                startActivity(intent)
                finish()
            } else if (countOfvalue % 3 == 0 && countOfvalue >= 3 && countOfvalue < 6 && !isSuccess) { //3초마다 재연결을 해줘야 되므로.
                getFirstUserInfo()
            } else if (countOfvalue % 3 == 0 && countOfvalue == 6 && !isSuccess) { //마지막까지 통신 안될시에는 사용자에게 알려주기.
                binding.tvCount.text = "카운트종료"
                getFirstUserInfo()
                Toast.makeText(this@SplashActivity, "데이터 통신 실패하였습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

        }.addTo(compositeDisposable)

    override fun onDestroy() {
        super.onDestroy()

        //프로세스 종료시 통신작업 중단.
        compositeDisposable.dispose()
    }

    private fun getFirstUserInfo() {
        Util.showProgress(this@SplashActivity)

        userRepository.getUserInfo(firstQuery, Const.START_PAGE, Const.PER_PAGE_LIST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Util.closeProgress()
                userList = it.body()?.items
                isSuccess = true
            }, {
                Util.closeProgress()
                isSuccess = false
                countResponse = true
            }).addTo(compositeDisposable)
    }
}