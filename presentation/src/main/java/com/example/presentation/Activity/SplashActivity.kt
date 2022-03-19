package com.example.presentation.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.DataSource.RemoteDataSource
import com.example.presentation.DataSource.RemoteDataSourceImpl
import com.example.presentation.Fragment.GitRetrofit
import com.example.presentation.Model.GitUserModel
import com.example.presentation.Repository.Repository
import com.example.presentation.Repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    private var mHandler = Handler()
    private var failCount = 0
    var gitRepoName : GitUserModel? = null

//    lateinit var repository: Repository
//    lateinit var remoteDataSource: RemoteDataSource   //lateinit 선언

//    val repository : Repository by lazy {
//        val remoteDatasoruce = RemoteDataSourceImpl(GitRetrofit.repoService)
//        RepositoryImpl(remoteDatasoruce)
//
//    }

    //by lazy
    private val repository : Repository by lazy {
        RepositoryImpl(RemoteDataSourceImpl(GitRetrofit.repoService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repository.getUserName().enqueue(object : Callback<GitUserModel> {
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

            }
        })
    }


    //유저정보 가져오는 것 실패했을 때 1번 더 불러주게 하는 함수
//    private fun getUsersFailed(){
//        if(failCount==0) {
//            getUserName()
//            failCount = 1
//        }
//    }
}