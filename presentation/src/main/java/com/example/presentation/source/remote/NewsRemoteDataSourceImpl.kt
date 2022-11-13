package com.example.presentation.source.remote

import com.example.presentation.retrofit.ApiService
import com.example.presentation.NewsData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRemoteDataSourceImpl : NewsRemoteDataSource {

    override fun getNews(category : String?): Call<NewsData> {
        val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build();
        val service = retrofit.create(ApiService::class.java)
        return if(category == null){
            service.requestNews()   //TopNews인 경우
        }else{
            service.requestCategoryNews(category = category)    //categoryNews인 경우
        }
    }


}