package com.example.presentation.source.remote

import com.example.presentation.retrofit.ApiService
import com.example.presentation.NewsData
import com.example.presentation.retrofit.RetrofitHelper
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRemoteDataSourceImpl : NewsRemoteDataSource {

    override fun getNews(): Call<NewsData> {
        return RetrofitHelper.retrofit.requestNews()   //TopNews인 경우
    }

    override fun getNewsCategory(category: String): Call<NewsData> {
        return RetrofitHelper.retrofit.requestCategoryNews(category = category)    //categoryNews인 경우
    }


}