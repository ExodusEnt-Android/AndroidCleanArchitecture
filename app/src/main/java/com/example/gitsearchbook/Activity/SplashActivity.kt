package com.example.gitsearchbook.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gitsearchbook.Fragment.GitRetrofit
import com.example.gitsearchbook.Model.GitUserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    private var mHandler = Handler()
    lateinit var gitRepoName : GitUserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GitRetrofit.userService.getUserName("hello",1,10).enqueue(object : Callback<GitUserModel> {
            override fun onResponse(call: Call<GitUserModel>, response: Response<GitUserModel>) {
                gitRepoName = response.body()!!
                Log.d("asdasd","asdasd::::"+gitRepoName.items)
            }

            override fun onFailure(call: Call<GitUserModel>, t: Throwable) {
                Log.d("asdasd","failCall:     "+call)
            }
        })

        //고정 2초 후 메인 화면 진입
        mHandler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}