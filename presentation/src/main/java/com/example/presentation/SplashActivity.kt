package com.example.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.Room.UserDB
import com.example.presentation.Room.UserModel

class SplashActivity : AppCompatActivity(){

    private var mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mHandler.postDelayed({
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java)).apply {
                val newUser = UserModel("mingue0605@naver.com","alsrb123")
                val db = UserDB.getInstance(this@SplashActivity)
                db?.userDao()?.insert(newUser)

//                Log.d("mingue123 -> ", db?.userDao()?.getAll().toString())
            }
        },2000)
    }
}