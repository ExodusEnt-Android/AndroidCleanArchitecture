package com.example.domain.usecase

import com.example.domain.entity.DataArticlesEntity
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SelectLocalNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(): Flow<List<DataArticlesEntity>> {
        return newsRepository.getAllArticles()
    }
}
