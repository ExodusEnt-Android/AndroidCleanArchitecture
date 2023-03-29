package org.techtown.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.techtown.remote.BuildConfig
import org.techtown.remote.retrofit.ApiService
import org.techtown.remote.retrofit.ServerIp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder().baseUrl(ServerIp.baseUrl).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideOkhttpClient(
        networkInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(networkInterceptor)
        .addInterceptor(httpLogInterceptor()).build()

    @Provides
    @Singleton
    fun provideNetworkInterceptor(): Interceptor = Interceptor { chain ->
        val requestWithHeader =
            chain.request().newBuilder().addHeader("X-Api-Key", "d8bea525326543899b50f67bf0a8c2f1")
        chain.proceed(requestWithHeader.build())
    }

    @Provides
    @Singleton
    fun httpLogInterceptor(): HttpLoggingInterceptor {
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