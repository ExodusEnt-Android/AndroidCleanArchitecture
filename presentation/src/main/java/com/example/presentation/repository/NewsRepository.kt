package com.example.presentation.repository

import com.example.presentation.NewsData
import retrofit2.Call

interface NewsRepository {
    fun getNews(
        category: String? = null
    ): Call<NewsData>
}