package com.example.gitsearchbook.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gitsearchbook.Fragment.GitRetrofit
import com.example.gitsearchbook.Model.GitRepoModel
import com.example.gitsearchbook.R
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

public class SplashActivity : AppCompatActivity() {

    private var mHandler = Handler()
    var gitRepoName : ArrayList<GitRepoModel>? = ArrayList<GitRepoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GitRetrofit.userService.getUserName("mingue").enqueue(object : retrofit2.Callback<ArrayList<GitRepoModel>> {
            override fun onResponse(call: Call<ArrayList<GitRepoModel>>, response: Response<ArrayList<GitRepoModel>>) {
                gitRepoName = response.body()
                Log.d("asdasd","asdasd::::"+gitRepoName)
            }

            override fun onFailure(call: Call<ArrayList<GitRepoModel>>, t: Throwable) {
                Log.d("asdasd","asdasd:"+call)
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