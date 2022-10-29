package com.example.presentation.source.remote

import com.example.presentation.const.Const
import com.example.presentation.model.Article
import com.example.presentation.model.BaseDataModel
import com.example.presentation.retrofit.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//실세  topnewremote datasource의 구현체
class TopNewsRemoteDataSourceImpl(private val retrofitHelper:RetrofitHelper):TopNewsRemoteDataSource {
    override fun getTopHeadLines(
        category: String?,
        page: Int,
        pageSize: Int
    ): Call<BaseDataModel<Article>> {
      return  retrofitHelper.apiService.getTopHeadLines(page = page, pageSize = Const.PageSize)
    }

}