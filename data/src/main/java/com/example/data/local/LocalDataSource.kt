/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.data.local

import com.example.data.model.Articles
import kotlinx.coroutines.flow.Flow


/**
 * @see
 * */

interface LocalDataSource{

    suspend fun getAll() : Flow<List<Articles>>

    suspend fun insert(items : Articles)

    suspend fun deleteArticle(url : String)
}