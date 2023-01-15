package org.techtown.presentation.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.techtown.presentation.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NewsService {
    private const val BASE_URL = "https://newsapi.org/"

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(networkInterceptor())
            .addInterceptor(httpLogInterceptor())
            .build()
    }

    private fun networkInterceptor(): Interceptor = Interceptor { chain ->
        val requestWithHeader =
            chain.request().newBuilder().addHeader("X-Api-Key", "d8bea525326543899b50f67bf0a8c2f1")
        chain.proceed(requestWithHeader.build())
    }

    //http 통신 로그 intercept해서 보여줌. -> 디버깅용
    private fun httpLogInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        return loggingInterceptor.setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {//디버그가 아니면  로그가 안보이게 해준다.
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }

}