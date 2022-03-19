package com.example.presentation.DataSource

import com.example.presentation.ApiService
import com.example.presentation.Fragment.GitRetrofit
import com.example.presentation.Model.GitUserModel
import retrofit2.Call
import retrofit2.Response

class RemoteDataSourceImpl(
    private val apiService : ApiService
) : RemoteDataSource {
    //스플래쉬에서 쓸 것
    override fun getUserName(): Call<GitUserModel> {
        return apiService.getUserName("mingue",1,10)
    }

    //UserSearchFragmnt 쓸 것
    override fun getUserInfo(userName: String): Call<GitUserModel> {
        return apiService.getUserName(userName,1,10)
    }
}