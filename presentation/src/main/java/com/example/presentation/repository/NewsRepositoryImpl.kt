/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.repository

import com.example.presentation.Articles
import com.example.presentation.NewsData
import com.example.presentation.datasource.local.LocalDataSource
import com.example.presentation.datasource.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow


/**
 * @see
 * */

class NewsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {
    override suspend fun getNews(country : String, category : String?): Flow<NewsData> = remoteDataSource.getNews(country, category)

    override suspend fun getAll(): Flow<List<Articles>> = localDataSource.getAll()

    override fun insert(items: Articles, callback: () -> Unit) {
        localDataSource.insert(items){
            callback()
        }
    }

    override fun deleteArticle(url: String, callback: () -> Unit) {
        localDataSource.deleteArticle(url){
            callback()
        }
    }


}