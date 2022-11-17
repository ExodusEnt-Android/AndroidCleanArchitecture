package org.techtown.presentation.datasource.local

import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.model.Articles


/**
 * @see
 * */

class NewsLocalDatasourceImpl(private val database: AppDatabase) : NewsLocalDatasource {
    override fun getAllSavedArticles(): List<Articles> {
        return database.articleDao().getAllArticles()
    }

    override fun insertArticle(articles: Articles, callback: () -> Unit) {
        database.articleDao().insertArticle(articles)
        callback()
    }

    override fun deleteArticle(url: String, callback: () -> Unit) {
        database.articleDao().deleteArticle(url)
        callback()
    }

}