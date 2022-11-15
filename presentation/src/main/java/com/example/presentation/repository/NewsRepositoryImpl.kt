package com.example.presentation.repository

import com.example.presentation.Articles
import com.example.presentation.NewsData
import com.example.presentation.source.local.SavedNewsLocalDataSource
import com.example.presentation.source.remote.NewsRemoteDataSource
import retrofit2.Call

class NewsRepositoryImpl(
    private val topNewsRemoteDataSource: NewsRemoteDataSource,
    private val savedNewsLocalDataSource: SavedNewsLocalDataSource
) : NewsRepository {
    override fun getNews(category: String?): Call<NewsData> {
        return topNewsRemoteDataSource.getNews(category)
    }

    override fun getSavedNews(callback: (List<Articles>?, Throwable?) -> Unit) {
        return savedNewsLocalDataSource.getSavedNews(callback)
    }

    override fun saveNews(articles: Articles, callback: () -> Unit) {
        return savedNewsLocalDataSource.saveNews(articles, callback)
    }

    override fun deleteNews(url: String, callback: () -> Unit) {
        return savedNewsLocalDataSource.deleteNews(url, callback)
    }
}