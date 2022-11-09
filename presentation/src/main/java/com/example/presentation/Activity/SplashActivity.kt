package com.example.presentation.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.Base.BaseActivity
import com.example.presentation.Room.AppDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            if(AppDB.getInstance(this@SplashActivity)?.loginDao()?.getAll()?.size != 0){
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }

    }
}