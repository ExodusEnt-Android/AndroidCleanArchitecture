package com.example.presentation.repository

import com.example.presentation.model.Article
import com.example.presentation.model.BaseDataModel
import com.example.presentation.source.local.SavedNewsLocalDataSource
import com.example.presentation.source.remote.TopNewsRemoteDataSource
import retrofit2.Call

class TopNewsRepositoryImpl(private val topNewsRemoteDataSource: TopNewsRemoteDataSource,
                            private val savedNewsLocalDataSource: SavedNewsLocalDataSource):TopNewsRepository {
    override fun getTopHeadLines(
        category: String?,
        page: Int,
        pageSize: Int
    ): Call<BaseDataModel<Article>> {
        return topNewsRemoteDataSource.getTopHeadLines(category, page, pageSize)
    }

    override fun getSavedArticleList(callback: (List<Article>?, Throwable?) -> Unit) {
       return savedNewsLocalDataSource.getSavedArticleList(callback)
    }
}