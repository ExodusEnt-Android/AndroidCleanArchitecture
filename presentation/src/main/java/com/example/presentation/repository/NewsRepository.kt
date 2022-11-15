package com.example.presentation.repository

import com.example.presentation.Articles
import com.example.presentation.NewsData
import retrofit2.Call

interface NewsRepository {
    fun getNews(
        category: String? = null
    ): Call<NewsData>

    fun getSavedNews(callback: (List<Articles>?, Throwable?) -> Unit)

    fun saveNews(articles: Articles, callback : () -> Unit)

    fun deleteNews(url : String, callback: () -> Unit)
}