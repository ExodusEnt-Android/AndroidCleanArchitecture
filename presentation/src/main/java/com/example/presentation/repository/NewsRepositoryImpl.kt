package com.example.presentation.repository

import com.example.presentation.NewsData
import com.example.presentation.source.remote.NewsRemoteDataSource
import retrofit2.Call

class NewsRepositoryImpl(private val topNewsRemoteDataSource: NewsRemoteDataSource) : NewsRepository {
    override fun getNews(category: String?): Call<NewsData> {
        return topNewsRemoteDataSource.getNews(category)
    }
}