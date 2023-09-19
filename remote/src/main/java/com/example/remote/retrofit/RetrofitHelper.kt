package com.example.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val retrofit: ApiService = Retrofit.Builder().baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create()).build().create(com.example.remote.retrofit.ApiService::class.java)
}