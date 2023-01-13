package com.example.presentation.Activity

import android.content.Intent
import android.os.Bundle
import com.example.presentation.PreferenceUtil
import com.example.presentation.R
import com.example.presentation.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
//            delay(3000)
            //아이디 2개 저장
            PreferenceUtil.setPreference(this@SplashActivity, "mingue0605", "alsrb123")
            PreferenceUtil.setPreference(this@SplashActivity, "mingue1234", "alsrb1234")

            //로그인 상태 아닐 경우
            if(PreferenceUtil.getPreferenceBool(this@SplashActivity, "Login", false)){
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()

            }else{  //로그인 상태일 경우
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }


    }
}