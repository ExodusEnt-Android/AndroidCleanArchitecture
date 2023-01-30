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
import com.example.local.model.LocalArticles.Companion.fromData
import com.example.local.model.LocalArticles.Companion.toData
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
        emit(result.map {
            it.toData()
        })
    }.flowOn(Dispatchers.IO)

    override fun insert(items: Articles, callback: () -> Unit) {
        appDB.newsDao().insert(items.fromData())
        callback()
    }

    override fun deleteArticle(url: String, callback: () -> Unit) {
        appDB.newsDao().deleteArticle(url)
        callback()
    }

}