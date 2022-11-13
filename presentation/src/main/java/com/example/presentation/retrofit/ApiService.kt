package com.example.presentation.retrofit

import com.example.presentation.NewsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?country=us&apiKey=c1a4068f987c4e8c97c9e44ec52d29a8")
    fun requestNews(): Call<NewsData>

    @GET("top-headlines")
    fun requestCategoryNews(
        @Query("category") category : String,
        @Query("apiKey") apiKey:String  = "c1a4068f987c4e8c97c9e44ec52d29a8"
    ) : Call<NewsData>
}