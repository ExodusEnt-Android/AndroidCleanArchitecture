package com.example.presentation.source.remote

import com.example.presentation.model.Article
import com.example.presentation.model.BaseDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query


// 탑 뉴스 remote datasource interface 모음
interface TopNewsRemoteDataSource {
    fun getTopHeadLines(
        category: String? = null,//optional
        page:Int,
        pageSize:Int
    ):Call<BaseDataModel<Article>>
}