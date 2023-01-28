package org.techtown.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.techtown.data.model.DataArticles
import org.techtown.data.model.DataNewsRootModel
import org.techtown.data.model.DataSource
import org.techtown.presentation.mapper.FloPresentationMapper
import org.techtown.presentation.model.Articles.Companion.fromFloData
import org.techtown.presentation.model.Articles.Companion.toFloData
import org.techtown.presentation.model.Source.Companion.fromFloData
import org.techtown.presentation.model.Source.Companion.toFloData

data class NewsRootModel(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<Articles> = listOf()
) {
    companion object : FloPresentationMapper<NewsRootModel, DataNewsRootModel> {
        override fun NewsRootModel.toFloData(): DataNewsRootModel {
            return DataNewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.toFloData()
                }

            )
        }

        override fun DataNewsRootModel.fromFloData(): NewsRootModel {
            return NewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.fromFloData()
                }
            )
        }

    }
}

@Parcelize
data class Source(
    var id: String? = null,
    var name: String? = null
) : Parcelable {
    companion object : FloPresentationMapper<Source, DataSource> {
        override fun Source.toFloData(): DataSource =
            DataSource(
                id = this.id,
                name = this.name
            )

        override fun DataSource.fromFloData(): Source {
            return Source(
                id = this.id,
                name = this.name
            )
        }
    }
}

@Parcelize
data class Articles(
    var source: Source? = Source(),
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null,
    var isLoading: Boolean = false,
) : Parcelable {
    companion object : FloPresentationMapper<Articles, DataArticles> {
        override fun Articles.toFloData(): DataArticles =
            DataArticles(
                source = this.source?.toFloData(),
                author = this.author,
                title = this.title,
                description = this.description,
                url = this.url,
                urlToImage = this.urlToImage,
                publishedAt = this.publishedAt,
                content = this.content,
                isLoading = this.isLoading
            )

        override fun DataArticles.fromFloData(): Articles {
            return Articles(
                source = this.source?.fromFloData(),
                author = this.author,
                title = this.title,
                description = this.description,
                url = this.url,
                urlToImage = this.urlToImage,
                publishedAt = this.publishedAt,
                content = this.content,
                isLoading = this.isLoading
            )
        }
    }
}