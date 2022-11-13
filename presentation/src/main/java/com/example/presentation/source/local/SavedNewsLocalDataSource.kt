package com.example.presentation.source.local

import com.example.presentation.Articles
import retrofit2.Call
import retrofit2.Callback

interface SavedNewsLocalDataSource {

    fun getSavedNews(callback: (List<Articles>?, Throwable?) -> Unit)

    fun saveNews(articles: Articles, callback : () -> Unit)

    fun deleteNews(url : String, callback: () -> Unit)
}