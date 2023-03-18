package org.techtown.local.feature.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.techtown.data.source.local.news.LocalDataSource
import org.techtown.local.feature.database.ArticlesDao
import org.techtown.local.feature.database.database.AppDatabase
import org.techtown.local.feature.news.LocalDataSourceImpl


@Module
@InstallIn(ActivityRetainedComponent::class)
object LocalModule {

    @Provides
    fun provideNewsDao(
        @ApplicationContext context: Context
    ) : ArticlesDao = AppDatabase.getInstance(context).articleDao()

    @Provides
    fun provideNewsLocalDataSource(
        articlesDao: ArticlesDao
    ) : LocalDataSource = LocalDataSourceImpl(articlesDao)
}