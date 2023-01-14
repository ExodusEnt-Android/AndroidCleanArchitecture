/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author __Jung Sang Min__ <jnugg0819@myloveidol.com>
 * Description:
 *
 * */

package org.techtown.presentation.repository

import kotlinx.coroutines.flow.Flow
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.NewsRootModel
import retrofit2.http.Query


/**
 * @see
 * */

interface NewsRepository {

    suspend fun getTopHeadlinesArticles(
        @Query("country") country : String,
        @Query("category") category : String?,
        @Query("pageSize") pageSize: Int,
        @Query("page") offset : Int
    ) : Flow<NewsRootModel>

    suspend fun getAllSavedArticles() : Flow<List<Articles>>

    fun insertArticle(articles: Articles, callback : () -> Unit)

    fun deleteArticle(url: String, callback : () -> Unit)
}