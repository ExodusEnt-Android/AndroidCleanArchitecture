package org.techtown.remote.retrofit

import org.techtown.remote.model.RemoteNewsRootModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @see
 * */

interface ApiService {
    @GET("v2/top-headlines/")
    suspend fun getTopHeadlinesArticles(
        @Query("country") country: String,
        @Query("category") category: String?,
        @Query("pageSize") pageSize: Int,
        @Query("page") offset: Int
    ): Response<RemoteNewsRootModel>
}