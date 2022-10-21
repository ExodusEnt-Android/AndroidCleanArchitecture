package com.example.presentation.retrofit

import ApiService
import com.example.presentation.BuildConfig
import okhttp3.Interceptor
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
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(networkInterceptor())
            .addInterceptor(httpLogInterceptor())
            .build()
    }


    //헤더에 api key 추가
    private fun networkInterceptor(): Interceptor = Interceptor { chain ->
        val requestWithHeader = chain.request().newBuilder().addHeader("X-Api-Key", BuildConfig.API_KEY)
        chain.proceed(requestWithHeader.build())
    }



    //http 통신 로그 intercept해서 보여줌. -> 디버깅용
    private fun httpLogInterceptor() :HttpLoggingInterceptor{
        val loggingInterceptor =  HttpLoggingInterceptor()
        return loggingInterceptor.setLevel(if(BuildConfig.DEBUG){
            HttpLoggingInterceptor.Level.BODY
        }else{//디버그가 아니면  로그가 안보이게 해준다.
            HttpLoggingInterceptor.Level.NONE
        })
    }
}