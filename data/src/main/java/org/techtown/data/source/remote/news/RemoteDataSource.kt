package org.techtown.data.source.remote.news

import kotlinx.coroutines.flow.Flow
import org.techtown.data.model.DataNewsRootModel

/**
 * @see getTopHeadlinesArticles 서버에서 Top News관련 데이터를 가지고옴.
 * */

interface RemoteDataSource {
    suspend fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Flow<DataNewsRootModel>
}