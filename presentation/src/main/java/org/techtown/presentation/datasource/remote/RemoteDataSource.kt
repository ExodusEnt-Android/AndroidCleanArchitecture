package org.techtown.presentation.datasource.remote

import kotlinx.coroutines.flow.Flow
import org.techtown.presentation.model.NewsRootModel
import retrofit2.http.Query


/**
 * @see getTopHeadlinesArticles 서버에서 Top News관련 데이터를 가지고옴.
 * */

interface RemoteDataSource {
    suspend fun getTopHeadlinesArticles(
        @Query("country") country: String,
        @Query("category") category: String?,
        @Query("pageSize") pageSize: Int,
        @Query("page") offset: Int
    ): Flow<NewsRootModel>
}