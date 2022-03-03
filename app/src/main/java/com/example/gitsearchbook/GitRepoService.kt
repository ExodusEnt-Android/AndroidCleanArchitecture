package com.example.gitsearchbook

import com.example.gitsearchbook.Model.GitRepoModel
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path


interface GitRepoService {
    @GET("users/{user}/repos")
    fun getUserRepositories(@Path("user") userName: String): Call<ArrayList<GitRepoModel>>
}