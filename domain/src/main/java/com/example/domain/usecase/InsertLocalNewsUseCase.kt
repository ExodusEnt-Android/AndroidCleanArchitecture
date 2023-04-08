package com.example.domain.usecase

import com.example.domain.entity.DataArticlesEntity
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class InsertLocalNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(
        articles: DataArticlesEntity
    ) {
        newsRepository.insertArticle(articles)
    }
}