/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.data.repository

import com.example.data.local.LocalDataSource
import com.example.data.model.ArticlesDataModel
import com.example.data.model.DataNewsModel
import com.example.data.remote.RemoteDataSource
import com.example.domain.entity.ArticlesEntity
import com.example.domain.entity.NewsModelEntity
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {
    override suspend fun getNews(country: String, category: String?): Flow<NewsModelEntity> = remoteDataSource.getNews(country, category)

    override suspend fun getAll(): Flow<List<ArticlesEntity>> = localDataSource.getAll()

    override suspend fun insert(items: ArticlesDataModel) {
        localDataSource.insert(items)
    }

    override suspend fun deleteArticle(url: String) {
        localDataSource.deleteArticle(url)
    }
}