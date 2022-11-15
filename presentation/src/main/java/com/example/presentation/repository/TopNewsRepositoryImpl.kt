package com.example.presentation.repository

import com.example.presentation.model.Article
import com.example.presentation.source.local.SavedNewsLocalDataSource
import com.example.presentation.source.remote.TopNewsRemoteDataSource
import io.reactivex.rxjava3.core.Single

class TopNewsRepositoryImpl(private val topNewsRemoteDataSource: TopNewsRemoteDataSource,
                            private val savedNewsLocalDataSource: SavedNewsLocalDataSource):TopNewsRepository {
    override fun getTopHeadLines(
        category: String?,
        page: Int,
        pageSize: Int
    ): Single<List<Article>> {
        return topNewsRemoteDataSource.getTopHeadLines(category, page, pageSize).map {
            if(it.status == "ok"){
                it.articles?: emptyList()
            }else{
                throw Exception(it.message)
            }
        }
    }

    override fun getSavedArticleList(callback: (List<Article>?, Throwable?) -> Unit) {
       return savedNewsLocalDataSource.getSavedArticleList(callback)
    }

    override fun saveArticle(article: Article, callback: () -> Unit) {
        return savedNewsLocalDataSource.saveArticle(article,callback)
    }

    override fun removeArticle(article: Article, callback: () -> Unit) {
        return savedNewsLocalDataSource.removeArticle(article, callback)
    }
}