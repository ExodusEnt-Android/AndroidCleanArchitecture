package org.techtown.presentation.datasource.local

import org.techtown.presentation.model.Articles


/**
 * @see
 * */

interface NewsLocalDatasource {
    fun getAllSavedArticles() : List<Articles>

    fun insertArticle(articles: Articles, callback : () -> Unit)

    fun deleteArticle(url: String, callback: () -> Unit)
}