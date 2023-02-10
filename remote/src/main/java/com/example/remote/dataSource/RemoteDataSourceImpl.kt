/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.remote.dataSource

import com.example.data.model.DataNewsModel
import com.example.data.remote.RemoteDataSource
import com.example.local.model.RemoteNewsModel.Companion.toData
import com.example.remote.retrofit.RetrofitHelper
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
    ) : Flow<DataNewsModel> = flow {
        val response = RetrofitHelper.retrofit.requestNews(country, category)

        if(response.isSuccessful){
            response.body()?.let {
                emit(it.toData())
            }
        }

    }.flowOn(Dispatchers.IO)



}