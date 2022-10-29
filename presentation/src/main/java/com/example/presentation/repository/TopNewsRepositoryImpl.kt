package com.example.presentation.repository

import com.example.presentation.model.Article
import com.example.presentation.model.BaseDataModel
import com.example.presentation.source.remote.TopNewsRemoteDataSource
import retrofit2.Call

class TopNewsRepositoryImpl(private val topNewsRemoteDataSource: TopNewsRemoteDataSource):TopNewsRepository {
    override fun getTopHeadLines(
        category: String?,
        page: Int,
        pageSize: Int
    ): Call<BaseDataModel<Article>> {
        return topNewsRemoteDataSource.getTopHeadLines(category, page, pageSize)
    }
}