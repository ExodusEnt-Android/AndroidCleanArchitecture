package com.example.gitsearchbook

import com.example.gitsearchbook.Model.GitUserModel
import com.google.gson.JsonArray
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {
    @GET("users/{user}/repos")
    fun getUserRepositories(@Path("user") userName: String?): Call<GitUserModel?>?
}