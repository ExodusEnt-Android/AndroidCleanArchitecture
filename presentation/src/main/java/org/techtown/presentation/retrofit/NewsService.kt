package org.techtown.presentation.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.techtown.presentation.model.NewsRootModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines/")
    fun getArticles(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String,
        @Query("category") category : String?,
        @Query("pageSize") pageSize: Int,
        @Query("page") offset : Int
    ) : Call<NewsRootModel>

    companion object {
        private const val BASE_URL = "https://newsapi.org/"

        private val gson =
            GsonBuilder()
                .setLenient()
                .create()

        fun create() : NewsService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(NewsService::class.java)
        }
    }

}