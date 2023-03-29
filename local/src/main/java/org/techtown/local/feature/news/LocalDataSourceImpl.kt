package org.techtown.local.feature.news

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.techtown.data.model.DataArticles
import org.techtown.data.source.local.news.LocalDataSource
import org.techtown.local.feature.database.ArticlesDao
import org.techtown.local.feature.database.database.AppDatabase
import org.techtown.local.feature.model.LocalArticles.Companion.fromData
import org.techtown.local.feature.model.LocalArticles.Companion.toData
import javax.inject.Inject


/**
 * @see
 * */

class LocalDataSourceImpl @Inject constructor(
    private val articlesDao: ArticlesDao
) : LocalDataSource {
    override suspend fun getAllArticles(): Flow<List<DataArticles>> = flow {
        val result = articlesDao.getAllArticles()
        emit(result.map {
            it.toData()
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun addArticle(articles: DataArticles) {
        withContext(Dispatchers.IO) {
            articlesDao.insertArticle(articles.fromData())
        }
    }

    override suspend fun removeArticle(url: String) {
        withContext(Dispatchers.IO) {
            articlesDao.deleteArticle(url)
        }
    }

}