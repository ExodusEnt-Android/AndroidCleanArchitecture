package org.techtown.data.model


/**
 * @see
 * */

data class DataNewsRootModel(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<DataArticles> = listOf()
)

data class DataSource(
    var id: String? = null,
    var name: String? = null
)

data class DataArticles(
    var source: DataSource? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null,
    var isLoading: Boolean = false,
)