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

    override fun addArticle(articles: Articles, callback: () -> Unit) {
        appDatabase.articleDao().insertArticle(articles)
        callback()
    }

    override fun removeArticle(url: String, callback: () -> Unit) {
        appDatabase.articleDao().deleteArticle(url)
        callback()
    }

}