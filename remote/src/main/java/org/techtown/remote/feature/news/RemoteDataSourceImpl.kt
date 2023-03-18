package org.techtown.remote.feature.news

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.techtown.data.model.DataNewsRootModel
import org.techtown.data.source.remote.news.RemoteDataSource
import org.techtown.remote.model.RemoteNewsRootModel.Companion.toData
import org.techtown.remote.retrofit.ApiService
import javax.inject.Inject


/**
 * @see
 * */

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteDataSource {
    override suspend fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Flow<DataNewsRootModel> = flow {
        val response = apiService.getTopHeadlinesArticles(country, category, pageSize, offset)

        if (response.isSuccessful) {
            response.body()?.let {
                emit(it.toData())
            }
        }
    }.flowOn(Dispatchers.IO)
}