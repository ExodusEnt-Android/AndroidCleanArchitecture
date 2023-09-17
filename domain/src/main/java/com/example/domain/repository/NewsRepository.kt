/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.domain.repository

import com.example.domain.entity.ArticlesEntity
import com.example.domain.entity.NewsModelEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository{

    suspend fun getNews(country : String, category : String?) : Flow<NewsModelEntity>

    suspend fun getAll() : Flow<List<ArticlesEntity>>

    suspend fun insert(items: ArticlesEntity)

    suspend fun deleteArticle(url : String)
}