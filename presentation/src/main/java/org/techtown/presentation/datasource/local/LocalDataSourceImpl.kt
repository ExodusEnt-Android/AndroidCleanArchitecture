package org.techtown.presentation.datasource.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.model.Articles


/**
 * @see
 * */

class LocalDataSourceImpl(
    private val appDatabase: AppDatabase
) : LocalDataSource {
    override suspend fun getAllArticles(): Flow<List<Articles>> = flow {
        val result = appDatabase.articleDao().getAllArticles()
        emit(result)
    }.flowOn(Dispatchers.IO)

    override suspend fun addArticle(articles: Articles) {
        appDatabase.articleDao().insertArticle(articles)
    }

    override suspend fun removeArticle(url: String) {
        appDatabase.articleDao().deleteArticle(url)
    }

}