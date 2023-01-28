package org.techtown.presentation.datasource.local

import kotlinx.coroutines.flow.Flow
import org.techtown.presentation.model.Articles


/**
 * @see getAllArticles 즐겨찾기된 아티클 전부 가져옴.
 * @see addArticle 아티클 즐겨찾기에 추가.
 * @see removeArticle 아티클 즐겨찾기에 제거.
 * */

interface LocalDataSource {
    suspend fun getAllArticles(): Flow<List<Articles>>
    suspend fun addArticle(articles: Articles)
    suspend fun removeArticle(url: String)
}