package org.techtown.presentation.repository

import org.techtown.presentation.datasource.local.NewsLocalDatasource
import org.techtown.presentation.datasource.remote.NewsRemoteDatasource
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.NewsRootModel
import retrofit2.Call


/**
 * @see
 * */

class NewsRepositoryImpl(
    private val newsRemoteDatasource: NewsRemoteDatasource,
    private val newsLocalDatasource: NewsLocalDatasource
) : NewsRepository {
    override fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Call<NewsRootModel> {
        return newsRemoteDatasource.getTopHeadlinesArticles(country, category, pageSize, offset)
    }

    override fun getAllSavedArticles(): List<Articles> {
        return newsLocalDatasource.getAllSavedArticles()
    }

    override fun insertArticle(articles: Articles, callback: () -> Unit) {
       newsLocalDatasource.insertArticle(articles, callback)
    }

    override fun deleteArticle(url: String, callback: () -> Unit) {
        newsLocalDatasource.deleteArticle(url, callback)
    }

}