/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.local.dataSource

import com.example.data.local.LocalDataSource
import com.example.data.model.Articles
import com.example.local.Room.AppDB
import com.example.local.Room.NewsDao
import com.example.local.model.LocalArticles.Companion.fromData
import com.example.local.model.LocalArticles.Companion.toData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * @see
 * */

class LocalDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao
) : LocalDataSource {
    override suspend fun getAll(): Flow<List<Articles>> = flow {
        val result = newsDao.getAll()
        emit(result.map {
            it.toData()
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun insert(items: Articles)  {
        withContext(Dispatchers.IO){
            newsDao.insert(items.fromData())
        }
    }

    override suspend fun deleteArticle(url: String) {
        withContext(Dispatchers.IO){
            newsDao.deleteArticle(url)
        }
    }

}