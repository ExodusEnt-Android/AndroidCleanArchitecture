/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author __Jung Sang Min__ <jnugg0819@myloveidol.com>
 * Description:
 *
 * */

package org.techtown.presentation.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.NewsRootModel
import org.techtown.presentation.retrofit.ApiService


/**
 * @see
 * */

class NewsRepositoryImpl(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : NewsRepository {
    override suspend fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Flow<NewsRootModel> = flow {
        val response = apiService.getTopHeadlinesArticles(country, category, pageSize, offset)

        if (response.isSuccessful) {
            response.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllSavedArticles(): Flow<List<Articles>> = flow {
        val result = appDatabase.articleDao().getAllArticles()
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun insertArticle(articles: Articles, callback: () -> Unit) {
        appDatabase.articleDao().insertArticle(articles)
        callback()
    }

    override fun deleteArticle(url: String, callback: () -> Unit) {
        appDatabase.articleDao().deleteArticle(url)
        callback()
    }


}