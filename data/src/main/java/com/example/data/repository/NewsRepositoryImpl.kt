/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.data.repository

import com.example.data.local.LocalDataSource
import com.example.data.model.Articles
import com.example.data.model.DataNewsModel
import com.example.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {
    override suspend fun getNews(country: String, category: String?): Flow<DataNewsModel> = remoteDataSource.getNews(country, category)

    override suspend fun getAll(): Flow<List<Articles>> = localDataSource.getAll()

    override suspend fun insert(items: Articles) {
        localDataSource.insert(items)
    }

    override suspend fun deleteArticle(url: String) {
        localDataSource.deleteArticle(url)
    }
}