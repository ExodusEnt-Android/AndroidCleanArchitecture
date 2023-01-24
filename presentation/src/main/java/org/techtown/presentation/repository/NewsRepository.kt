package org.techtown.presentation.repository

import kotlinx.coroutines.flow.Flow
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.NewsRootModel
import retrofit2.http.Query


/**
 * @see
 * */

interface NewsRepository {

    suspend fun getTopHeadlinesArticles(
        @Query("country") country : String,
        @Query("category") category : String?,
        @Query("pageSize") pageSize: Int,
        @Query("page") offset : Int
    ) : Flow<NewsRootModel>

    suspend fun getAllArticles() : Flow<List<Articles>>

    suspend fun insertArticle(articles: Articles)

    suspend fun deleteArticle(url: String)
}