package org.techtown.local.feature.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import org.techtown.data.source.local.news.LocalDataSource
import org.techtown.local.feature.database.ArticlesDao
import org.techtown.local.feature.database.database.AppDatabase
import org.techtown.local.feature.news.LocalDataSourceImpl


@Module
@InstallIn(ActivityRetainedComponent::class)
object LocalModule {

    @Provides
    fun provideNewsDao(
        appDatabase: AppDatabase
    ) : ArticlesDao = appDatabase.articleDao()

    @Provides
    fun provideNewsLocalDataSource(
        articlesDao: ArticlesDao
    ) : LocalDataSource = LocalDataSourceImpl(articlesDao)
}