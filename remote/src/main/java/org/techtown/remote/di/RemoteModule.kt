package org.techtown.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import org.techtown.data.source.remote.news.RemoteDataSource
import org.techtown.remote.feature.news.RemoteDataSourceImpl
import org.techtown.remote.retrofit.ApiService

@Module
@InstallIn(ActivityRetainedComponent::class)
object RemoteModule {

    @Provides
    fun provideNewsRemoteDataSource(
        apiService: ApiService
    ) : RemoteDataSource = RemoteDataSourceImpl(apiService)

}