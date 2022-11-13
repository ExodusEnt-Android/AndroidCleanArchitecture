package com.example.presentation.source.remote

import com.example.presentation.NewsData
import retrofit2.Call

interface NewsRemoteDataSource {
    fun getNews(
        category: String? = null
    ): Call<NewsData>
}