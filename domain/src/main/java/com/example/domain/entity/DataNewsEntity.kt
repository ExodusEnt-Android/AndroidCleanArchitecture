package com.example.domain.entity

data class DataNewsRootEntity(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<DataArticlesEntity> = listOf()
)

data class DataSourceEntity(
    var id: String? = null,
    var name: String? = null
)

data class DataArticlesEntity(
    var source: DataSourceEntity? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null,
    var isLoading: Boolean = false,
)