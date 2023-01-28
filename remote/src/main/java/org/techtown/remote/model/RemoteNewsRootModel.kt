package org.techtown.remote.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import org.techtown.data.model.DataArticles
import org.techtown.data.model.DataNewsRootModel
import org.techtown.data.model.DataSource
import org.techtown.remote.mapper.FloRemoteMapper
import org.techtown.remote.model.RemoteArticles.Companion.fromFloData
import org.techtown.remote.model.RemoteArticles.Companion.toFloData
import org.techtown.remote.model.RemoteNewsRootModel.Companion.fromFloData
import org.techtown.remote.model.RemoteNewsRootModel.Companion.toFloData
import org.techtown.remote.model.RemoteSource.Companion.fromFloData
import org.techtown.remote.model.RemoteSource.Companion.toFloData

@Parcelize
data class RemoteNewsRootModel(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: ArrayList<RemoteArticles> = arrayListOf()
) : Parcelable {

    companion object : FloRemoteMapper<RemoteNewsRootModel, DataNewsRootModel> {
        override fun RemoteNewsRootModel.toFloData(): DataNewsRootModel {
            return DataNewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.toFloData()
                } as ArrayList<DataArticles>

            )
        }

        override fun DataNewsRootModel.fromFloData(): RemoteNewsRootModel {
            return RemoteNewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.fromFloData()
                } as ArrayList<RemoteArticles>
            )
        }

    }
}

@Parcelize
data class RemoteSource(
    var id: String? = null,
    var name: String? = null
) : Parcelable {
    companion object : FloRemoteMapper<RemoteSource, DataSource> {
        override fun RemoteSource.toFloData(): DataSource =
            DataSource(
                id = this.id,
                name = this.name
            )


        override fun DataSource.fromFloData(): RemoteSource {
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
    companion object : FloRemoteMapper<RemoteArticles, DataArticles> {
        override fun RemoteArticles.toFloData(): DataArticles =
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

        override fun DataArticles.fromFloData(): RemoteArticles {
            return RemoteArticles(
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