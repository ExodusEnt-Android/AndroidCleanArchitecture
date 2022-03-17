package com.example.presentation

import com.example.presentation.Model.GitRepoModel
import com.example.presentation.Model.GitUserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users/{user}/repos")
    fun getUserRepositories(@Path("user") userName: String): Call<ArrayList<GitRepoModel>>
    @GET("search/users")
    fun getUserName(@Query("q") q: String, @Query("page") page: Int, @Query("per_page") per_page: Int): Call<GitUserModel>
}