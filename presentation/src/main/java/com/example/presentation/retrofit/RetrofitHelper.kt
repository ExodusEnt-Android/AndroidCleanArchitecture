package com.example.presentation.retrofit

import ApiService
import com.example.presentation.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper{
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ServerIp.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(httpLogCheck())
            .build()
    }

    private fun httpLogCheck() :HttpLoggingInterceptor{
        val loggingInterceptor =  HttpLoggingInterceptor()
        return loggingInterceptor.setLevel(if(BuildConfig.DEBUG){
            HttpLoggingInterceptor.Level.BODY
        }else{//디버그가 아니면  로그가 안보이게 해준다.
            HttpLoggingInterceptor.Level.NONE
        })
    }
}