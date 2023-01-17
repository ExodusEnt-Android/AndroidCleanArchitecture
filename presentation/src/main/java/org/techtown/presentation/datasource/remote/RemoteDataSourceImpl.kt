package org.techtown.presentation.datasource.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.techtown.presentation.model.NewsRootModel
import org.techtown.presentation.retrofit.ApiService


/**
 * @see
 * */

class RemoteDataSourceImpl(
    private val apiService: ApiService,
) : RemoteDataSource {
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
}