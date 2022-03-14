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
    private var failCount = 0
    var gitRepoName : GitUserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUserName()
    }

    //기본으로 설정한 유저 정보 가져오는 API
    private fun getUserName(){
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
                getUsersFailed()
            }
        })
    }

    //유저정보 가져오는 것 실패했을 때 1번 더 불러주게 하는 함수
    private fun getUsersFailed(){
        if(failCount==0) {
            getUserName()
            failCount = 1
        }
    }
}