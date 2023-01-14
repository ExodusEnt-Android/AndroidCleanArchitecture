package org.techtown.presentation.feature.splash

import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.techtown.androidcleanarchitecturecoroutine.R
import org.techtown.androidcleanarchitecturecoroutine.databinding.ActivitySplashBinding
import org.techtown.presentation.feature.main.MainActivity
import org.techtown.presentation.base.BaseActivity
import org.techtown.presentation.feature.login.LoginActivity
import org.techtown.presentation.util.PreferenceUtil

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun ActivitySplashBinding.onCreate() {
        initSet()
        goLoginActivity()
    }

    private fun initSet() {
        prefs = PreferenceUtil((applicationContext))
        registMember()

    }

    //로그인 할 수 있는 멤버 등록.
    private fun registMember() {
        prefs.setString("member1", "1111")
        prefs.setString("member2", "2222")
    }


    private fun goLoginActivity() {

        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)

            runOnUiThread {
                if (prefs.getBoolean(LoginActivity.LOGIN_STATUS, false)) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
                finish()
            }
        }
    }

    companion object {
        lateinit var prefs: PreferenceUtil
    }

}