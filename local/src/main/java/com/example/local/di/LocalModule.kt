/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.local.di

import android.content.Context
import com.example.data.local.LocalDataSource
import com.example.local.Room.AppDB
import com.example.local.Room.NewsDao
import com.example.local.dataSource.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

// ActivityRetainedComponent : Activity의 생명주기를 lifetime으로 갖습니다. 다만, Activity의 configuration change(디바이스 화면전환 등) 시에는 파괴되지 않고 유지됩니다.
@Module
@InstallIn(ActivityRetainedComponent::class)
object LocalModule {

    @Provides
    fun provideNewsDao(
      @ApplicationContext context : Context
    ): NewsDao = AppDB.getInstance(context).newsDao()

    @Provides
    fun provideLocalDataSource(
        newsDao: NewsDao
    ):LocalDataSource = LocalDataSourceImpl(newsDao)
}