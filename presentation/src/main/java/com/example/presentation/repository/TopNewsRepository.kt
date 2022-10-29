package com.example.presentation.repository

import com.example.presentation.model.Article
import com.example.presentation.model.BaseDataModel
import retrofit2.Call
import retrofit2.Callback

interface TopNewsRepository {
    fun getTopHeadLines(
        category: String? = null,//optional
        page:Int,
        pageSize:Int
    ): Call<BaseDataModel<Article>>
}