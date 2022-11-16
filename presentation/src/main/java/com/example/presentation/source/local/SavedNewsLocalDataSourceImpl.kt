package com.example.presentation.source.local

import com.example.presentation.model.Article
import com.example.presentation.room.LocalDataBase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Date: 2022/10/30
 * Author: idonghun
 *
 * Content: 저장한 게시글 가져오는  datasource의  실제  구현체 부분
 *
 * **/
class SavedNewsLocalDataSourceImpl(
    private val localDataBase: LocalDataBase
) : SavedNewsLocalDataSource {
    override fun getSavedArticleList(): Single<List<Article>> {
        return localDataBase.getNewsArticleDao().loadSavedNewsArticles()
    }

    override fun saveArticle(article: Article): Completable {
        return localDataBase.getNewsArticleDao().setSavedArticle(article)
    }

    override fun removeArticle(article: Article): Completable {
        return localDataBase.getNewsArticleDao().deleteSavedArticle(
            publishedAt = article.publishedAt.toString(),
            title = article.title.toString(),
            url = article.url.toString()
        )
    }
}