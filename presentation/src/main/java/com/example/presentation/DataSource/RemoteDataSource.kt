package com.example.presentation.DataSource

import com.example.presentation.Model.GitUserModel
import retrofit2.Call
import retrofit2.Response

interface RemoteDataSource {
    fun getUserName() : Call<GitUserModel>

    fun getUserInfo(userName : String) : Call<GitUserModel>

}