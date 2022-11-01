package org.techtown.presentation.splash

import android.content.Intent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.presentation.MainActivity
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseActivity
import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.databinding.ActivitySplashBinding
import org.techtown.presentation.enum.User
import org.techtown.presentation.login.LoginActivity
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private lateinit var database: AppDatabase

    override fun ActivitySplashBinding.onCreate() {
        initSet()
        goLoginActivity()
    }

    private fun initSet() {
        database = AppDatabase.getInstance(applicationContext)
    }

    private fun goLoginActivity() {
        Completable.timer(SPLASH_DELAY, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                CoroutineScope(Dispatchers.IO).launch {
                    //가져온 로그인 정보중에 유저아이디가 일치하고, 로그인한 여부가 파악이 되었다면 로그인한걸로 파악합니다.
                    val isLogined = database.loginDao().getIsLogined().any {
                        it.isLogined && (it.user_id == User.TESTUSER.userId || it.user_id == User.TESTUSER1.userId)
                    }

                    runOnUiThread {
                        if (isLogined) {
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        } else {
                            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        }
                    }

                }


                finish()
            }.subscribe().addTo(compositeDisposable = compositeDisposable)
    }

    companion object {
        const val SPLASH_DELAY = 700L
    }
}