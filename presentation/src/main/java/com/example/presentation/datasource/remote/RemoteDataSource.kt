/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.datasource.remote

import com.example.presentation.NewsData
import kotlinx.coroutines.flow.Flow


/**
 * @see
 * */

interface RemoteDataSource{

    suspend fun getNews(
        country : String,
        category : String?
    ) : Flow<NewsData>
}