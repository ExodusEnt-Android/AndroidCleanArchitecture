package org.techtown.presentation.retrofit

import org.techtown.presentation.model.NewsRootModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @see
 * */

interface ApiService {
    @GET("v2/top-headlines/")
    fun getTopHeadlinesArticles(
        @Query("country") country : String,
        @Query("category") category : String?,
        @Query("pageSize") pageSize: Int,
        @Query("page") offset : Int
    ) : Call<NewsRootModel>
}