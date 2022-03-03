package com.example.gitsearchbook

import com.example.gitsearchbook.Model.GitRepoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitUserService {
    @GET("https://api.github.com/search/users?q={user}&page=1&per_page=10")
    fun getUserName(@Query("user") userName: String): Call<ArrayList<GitRepoModel>>
}