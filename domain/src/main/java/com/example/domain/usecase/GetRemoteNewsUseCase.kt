package com.example.domain.usecase

import com.example.domain.entity.DataNewsRootEntity
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemoteNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Flow<DataNewsRootEntity> {
        return newsRepository.getTopHeadlinesArticles(
            country,
            category,
            pageSize,
            offset
        )
    }
}