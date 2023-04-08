package org.techtown.data.repository.news

import com.example.domain.entity.DataArticlesEntity
import com.example.domain.entity.DataNewsRootEntity
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.techtown.data.model.DataArticles.Companion.fromEntity
import org.techtown.data.model.DataArticles.Companion.toEntity
import org.techtown.data.model.DataNewsRootModel.Companion.fromEntity
import org.techtown.data.source.local.news.LocalDataSource
import org.techtown.data.source.remote.news.RemoteDataSource
import javax.inject.Inject


/**
 * @see
 * */

class NewsRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : NewsRepository {
    override suspend fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Flow<DataNewsRootEntity> = remoteDataSource.getTopHeadlinesArticles(
        country = country,
        category = category,
        pageSize = pageSize,
        offset = offset
    ).map { data ->
        data.fromEntity()
    }

    override suspend fun getAllArticles(): Flow<List<DataArticlesEntity>> =
        localDataSource.getAllArticles().map { savedArticles ->
            savedArticles.map { it.fromEntity() }
        }

    override suspend fun insertArticle(articles: DataArticlesEntity) {
        localDataSource.addArticle(articles = articles.toEntity())
    }

    override suspend fun deleteArticle(url: String) {
        localDataSource.removeArticle(url = url)
    }
}