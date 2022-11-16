/**
 * Copyright (C) 2022. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author __Jung Sang Min__ <jnugg0819@myloveidol.com>
 * Description:
 *
 * */

package org.techtown.presentation.repository

import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.NewsRootModel
import retrofit2.Call


/**
 * @see getTopHeadlinesArticles topHeadLines의 articles들을 가지고온다.
 * @see getAllSavedArticles 로컬에 저장된 topHeadLines articles들을 가지고 온다.
 * */

interface NewsRepository {
    fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ) : Call<NewsRootModel>

    fun getAllSavedArticles() : List<Articles>

    fun insertArticle(articles: Articles, callback : () -> Unit)

    fun deleteArticle(url: String, callback : () -> Unit)
}