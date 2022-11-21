package com.example.presentation.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
}