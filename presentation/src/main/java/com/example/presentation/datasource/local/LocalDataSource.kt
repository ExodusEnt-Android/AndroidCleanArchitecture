/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.datasource.local

import com.example.presentation.Articles
import kotlinx.coroutines.flow.Flow


/**
 * @see
 * */

interface LocalDataSource{

    suspend fun getAll() : Flow<List<Articles>>

    fun insert(items : Articles, callback: () -> Unit)

    fun deleteArticle(url : String, callback: () -> Unit)
}