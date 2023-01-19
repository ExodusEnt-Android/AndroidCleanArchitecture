/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.datasource.remote

import com.example.presentation.NewsData
import com.example.presentation.Room.AppDB
import com.example.presentation.retrofit.ApiService
import com.example.presentation.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @see
 * */

class RemoteDataSourceImpl(
): RemoteDataSource {

    override suspend fun getNews(
        country : String,
        category : String?
    ) : Flow<NewsData> = flow {
        val response = RetrofitHelper.retrofit.requestNews(country, category)

        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }

    }.flowOn(Dispatchers.IO)



}