package org.techtown.data.repository.news

import kotlinx.coroutines.flow.Flow
import org.techtown.data.model.DataArticles
import org.techtown.data.model.DataNewsRootModel


/**
 * @see
 * */

interface NewsRepository {

    suspend fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Flow<DataNewsRootModel>

    suspend fun getAllArticles(): Flow<List<DataArticles>>

    suspend fun insertArticle(articles: DataArticles)

    suspend fun deleteArticle(url: String)
}