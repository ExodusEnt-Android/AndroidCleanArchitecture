package com.example.presentation.activity

import android.content.Intent
import android.os.Looper
import android.os.Handler
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.const.Const
import com.example.presentation.databinding.ActivitySplashBinding
import com.example.presentation.util.PreferenceManager

class SplashActivity:BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun ActivitySplashBinding.onCreate() {

        val isUserAlreadyLogined = PreferenceManager.getPreference(this@SplashActivity,
            Const.PARAM_IS_LOGIN_SUCCESS,false) as Boolean

        Handler(Looper.getMainLooper()).postDelayed({
            if(isUserAlreadyLogined){
                goToMainScreen()
            }else{
                goToLoginScreen()
            }
            finish()
        }, 2000)//2초뒤에 로그인 화면으로
    }


    // 로그인 화면으로 가기
    private fun goToLoginScreen(){
        startActivity(Intent(this, LoginActivity::class.java))
    }

    //메인으로 가기
    private fun goToMainScreen(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}