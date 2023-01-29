package com.example.remote.retrofit

import com.example.data.model.DataNewsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun requestNews(
        @Query("country") country : String? = null,
        @Query("category") category : String? = null,
        @Query("apiKey") apiKey:String  = "c1a4068f987c4e8c97c9e44ec52d29a8"
    ) : Response<DataNewsModel>
}