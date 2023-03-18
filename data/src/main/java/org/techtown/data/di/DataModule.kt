package org.techtown.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import org.techtown.data.repository.news.NewsRepository
import org.techtown.data.repository.news.NewsRepositoryImpl
import org.techtown.data.source.local.news.LocalDataSource
import org.techtown.data.source.remote.news.RemoteDataSource

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataModule {

    @Provides
    fun provideNewsRepository(
        newsLocalDataSource: LocalDataSource,
        newsRemoteDataSource: RemoteDataSource
    ): NewsRepository = NewsRepositoryImpl(newsLocalDataSource, newsRemoteDataSource)
}