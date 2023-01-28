package org.techtown.data.source.local.news

import kotlinx.coroutines.flow.Flow
import org.techtown.data.model.DataArticles


/**
 * @see getAllArticles 즐겨찾기된 아티클 전부 가져옴.
 * @see addArticle 아티클 즐겨찾기에 추가.
 * @see removeArticle 아티클 즐겨찾기에 제거.
 * */

interface LocalDataSource {
    suspend fun getAllArticles(): Flow<List<DataArticles>>
    suspend fun addArticle(articles: DataArticles)
    suspend fun removeArticle(url: String)
}