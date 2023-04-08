package com.example.domain.usecase

import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteLocalNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(
        url: String
    ) {
        newsRepository.deleteArticle(
            url
        )
    }
}