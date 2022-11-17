package org.techtown.presentation.datasource.remote

import org.techtown.presentation.model.NewsRootModel
import org.techtown.presentation.retrofit.NewsService
import retrofit2.Call


/**
 * @see
 * */

class NewsRemoteDatasourceImpl(private val newsService: NewsService) : NewsRemoteDatasource {
    override fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Call<NewsRootModel> {
        return newsService.apiService.getTopHeadlinesArticles(country, category, pageSize, offset)
    }


}