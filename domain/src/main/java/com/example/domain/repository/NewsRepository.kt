package com.example.domain.repository

import com.example.domain.entity.DataArticlesEntity
import com.example.domain.entity.DataNewsRootEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Flow<DataNewsRootEntity>

    suspend fun getAllArticles(): Flow<List<DataArticlesEntity>>

    suspend fun insertArticle(articles: DataArticlesEntity)

    suspend fun deleteArticle(url: String)
}