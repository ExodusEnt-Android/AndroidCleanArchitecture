/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.data.di

import com.example.data.local.LocalDataSource
import com.example.data.remote.RemoteDataSource
import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

// ActivityRetainedComponent : Activity의 생명주기를 lifetime으로 갖습니다. 다만, Activity의 configuration change(디바이스 화면전환 등) 시에는 파괴되지 않고 유지됩니다.
@Module
@InstallIn(ActivityRetainedComponent::class)
object DataModule {

    @Provides
    fun provideNewsRepository(
        remoteDataSource : RemoteDataSource,
        localDataSource : LocalDataSource
    ) : NewsRepository = NewsRepositoryImpl(remoteDataSource, localDataSource)
}