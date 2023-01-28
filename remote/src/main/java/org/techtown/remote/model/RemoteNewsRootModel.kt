package org.techtown.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.techtown.data.model.DataArticles
import org.techtown.data.model.DataNewsRootModel
import org.techtown.data.model.DataSource
import org.techtown.remote.mapper.NewsRemoteMapper
import org.techtown.remote.model.RemoteArticles.Companion.fromData
import org.techtown.remote.model.RemoteArticles.Companion.toData
import org.techtown.remote.model.RemoteSource.Companion.fromData
import org.techtown.remote.model.RemoteSource.Companion.toData

@Parcelize
data class RemoteNewsRootModel(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<RemoteArticles> = listOf()
) : Parcelable {

    companion object : NewsRemoteMapper<RemoteNewsRootModel, DataNewsRootModel> {
        override fun RemoteNewsRootModel.toData(): DataNewsRootModel {
            return DataNewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.toData()
                }

            )
        }

        override fun DataNewsRootModel.fromData(): RemoteNewsRootModel {
            return RemoteNewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.fromData()
                }
            )
        }

    }
}

@Parcelize
data class RemoteSource(
    var id: String? = null,
    var name: String? = null
) : Parcelable {
    companion object : NewsRemoteMapper<RemoteSource, DataSource> {
        override fun RemoteSource.toData(): DataSource =
            DataSource(
                id = this.id,
                name = this.name
            )


        override fun DataSource.fromData(): RemoteSource {
            return RemoteSource(
                id = this.id,
                name = this.name
            )
        }

    }
}

@Parcelize
data class RemoteArticles(
    var source: RemoteSource? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null,
    var isLoading: Boolean = false,
) : Parcelable {
    companion object : NewsRemoteMapper<RemoteArticles, DataArticles> {
        override fun RemoteArticles.toData(): DataArticles =
            DataArticles(
                source = this.source?.toData(),
                author = this.author,
                title = this.title,
                description = this.description,
                url = this.url,
                urlToImage = this.urlToImage,
                publishedAt = this.publishedAt,
                content = this.content,
                isLoading = this.isLoading
            )

        override fun DataArticles.fromData(): RemoteArticles {
            return RemoteArticles(
                source = this.source?.fromData(),
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