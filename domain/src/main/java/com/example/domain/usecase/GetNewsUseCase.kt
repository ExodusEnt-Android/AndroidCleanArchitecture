/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.domain.usecase

import com.example.domain.entity.ArticlesEntity
import com.example.domain.entity.NewsModelEntity
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject  //그래들에서 의존성 가지고 있으면 안돼서

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
){

    suspend operator fun invoke(contry: String, category: String) : Flow<NewsModelEntity> {
        return newsRepository.getNews(category, contry)
    }
}