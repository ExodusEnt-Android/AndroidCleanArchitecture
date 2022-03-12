package com.example.presentation.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.Fragment.GitRetrofit
import com.example.presentation.Model.GitUserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    private var mHandler = Handler()
    var gitRepoName : GitUserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GitRetrofit.userService.getUserName("mingue",1,10).enqueue(object : Callback<GitUserModel> {
            override fun onResponse(call: Call<GitUserModel>, response: Response<GitUserModel>) {
                gitRepoName = response.body()!!
             //고정 2초 후 메인 화면 진입
                mHandler.postDelayed({
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java).apply {
                        this.putExtras( Bundle().apply { putParcelable(MainActivity.PARAM_USER, gitRepoName) })
                    })
                },2000)
            }

            override fun onFailure(call: Call<GitUserModel>, t: Throwable) {
                Log.d("asdasd","failCall:     "+call)
            }
        })
    }
}