package com.example.presentation.retrofit

import com.example.presentation.NewsData
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines?country=us&apiKey=c1a4068f987c4e8c97c9e44ec52d29a8")
    suspend fun requestNews(): Response<NewsData>

    @GET("v2/top-headlines")
    suspend fun requestCategoryNews(
        @Query("category") category : String,
        @Query("apiKey") apiKey:String  = "c1a4068f987c4e8c97c9e44ec52d29a8"
    ) : Response<NewsData>
}