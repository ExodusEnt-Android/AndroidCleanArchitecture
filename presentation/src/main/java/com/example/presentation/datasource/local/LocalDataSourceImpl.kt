/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.datasource.local

import com.example.presentation.Articles
import com.example.presentation.Room.AppDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * @see
 * */

class LocalDataSourceImpl(
    private val appDB: AppDB
) : LocalDataSource {
    override suspend fun getAll(): Flow<List<Articles>> = flow {
        val result = appDB.newsDao().getAll()
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun insert(items: Articles, callback: () -> Unit) {
        appDB.newsDao().insert(items)
        callback()
    }

    override fun deleteArticle(url: String, callback: () -> Unit) {
        appDB.newsDao().deleteArticle(url)
        callback()
    }

}