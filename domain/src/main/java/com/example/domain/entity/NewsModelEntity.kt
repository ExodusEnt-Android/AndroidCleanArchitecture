/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.domain.entity

data class NewsModelEntity(
    var dataArticlesModel: List<ArticlesEntity>
)

//data 모듈
class ArticlesEntity(
    var url: String,
    var author: String? = null,
    var title: String,
    var description: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null,
)

//presentation 모듈


