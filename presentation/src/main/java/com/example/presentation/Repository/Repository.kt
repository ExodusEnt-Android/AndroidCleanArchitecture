package com.example.presentation.Repository

import com.example.presentation.DataSource.RemoteDataSource
import com.example.presentation.Model.GitUserModel
import retrofit2.Call
import retrofit2.Response

interface Repository : RemoteDataSource {
    override fun getUserName() : Call<GitUserModel>

    override fun getUserInfo(userName : String) : Call<GitUserModel>
}