package org.techtown.presentation.repository

import kotlinx.coroutines.flow.Flow
import org.techtown.presentation.datasource.local.LocalDataSource
import org.techtown.presentation.datasource.remote.RemoteDataSource
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.NewsRootModel


/**
 * @see
 * */

class NewsRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : NewsRepository {
    override suspend fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Flow<NewsRootModel> = remoteDataSource.getTopHeadlinesArticles(
        country = country,
        category = category,
        pageSize = pageSize,
        offset = offset
    )

    override suspend fun getAllArticles(): Flow<List<Articles>> = localDataSource.getAllArticles()

    override suspend fun insertArticle(articles: Articles) {
        localDataSource.addArticle(articles = articles)
    }

    override suspend fun deleteArticle(url: String) {
        localDataSource.removeArticle(url = url)
    }
}