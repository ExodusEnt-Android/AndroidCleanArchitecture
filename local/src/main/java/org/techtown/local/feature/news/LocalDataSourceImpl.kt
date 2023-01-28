package org.techtown.local.feature.news

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.techtown.data.model.DataArticles
import org.techtown.data.source.local.news.LocalDataSource
import org.techtown.local.feature.database.database.AppDatabase
import org.techtown.local.feature.model.LocalArticles.Companion.fromData
import org.techtown.local.feature.model.LocalArticles.Companion.toData


/**
 * @see
 * */

class LocalDataSourceImpl(
    private val appDatabase: AppDatabase
) : LocalDataSource {
    override suspend fun getAllArticles(): Flow<List<DataArticles>> = flow {
        val result = appDatabase.articleDao().getAllArticles()
        emit(result.map {
            it.toData()
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun addArticle(articles: DataArticles) {
        withContext(Dispatchers.IO) {
            appDatabase.articleDao().insertArticle(articles.fromData())
        }
    }

    override suspend fun removeArticle(url: String) {
        withContext(Dispatchers.IO) {
            appDatabase.articleDao().deleteArticle(url)
        }
    }

}