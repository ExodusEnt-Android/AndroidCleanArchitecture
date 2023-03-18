package org.techtown.data.repository.news

import kotlinx.coroutines.flow.Flow
import org.techtown.data.model.DataArticles
import org.techtown.data.model.DataNewsRootModel
import org.techtown.data.source.local.news.LocalDataSource
import org.techtown.data.source.remote.news.RemoteDataSource
import javax.inject.Inject


/**
 * @see
 * */

class NewsRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : NewsRepository {
    override suspend fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Flow<DataNewsRootModel> = remoteDataSource.getTopHeadlinesArticles(
        country = country,
        category = category,
        pageSize = pageSize,
        offset = offset
    )

    override suspend fun getAllArticles(): Flow<List<DataArticles>> =
        localDataSource.getAllArticles()

    override suspend fun insertArticle(articles: DataArticles) {
        localDataSource.addArticle(articles = articles)
    }

    override suspend fun deleteArticle(url: String) {
        localDataSource.removeArticle(url = url)
    }
}