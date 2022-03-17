package com.example.presentation.Repository

import com.example.presentation.DataSource.RemoteDataSource
import com.example.presentation.DataSource.RemoteDataSourceImpl
import com.example.presentation.Model.GitUserModel
import retrofit2.Call
import retrofit2.Response

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun getUserName(): Call<GitUserModel> {
        return remoteDataSource.getUserName()
    }

    override fun getUserInfo(userName: String): Call<GitUserModel> {
        return remoteDataSource.getUserInfo(userName)
    }


}