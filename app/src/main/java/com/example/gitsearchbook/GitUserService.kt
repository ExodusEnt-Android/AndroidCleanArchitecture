package com.example.gitsearchbook

import com.example.gitsearchbook.Model.GitUserModel
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query


interface GitUserService {
    @GET("search/users")
    fun getUserName(@Query("q") q: String, @Query("page") page: Int, @Query("per_page") per_page: Int): Call<GitUserModel>
}