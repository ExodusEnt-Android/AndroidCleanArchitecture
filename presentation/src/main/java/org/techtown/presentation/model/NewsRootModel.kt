package org.techtown.presentation.model

import android.os.Parcelable
import com.example.domain.entity.DataArticlesEntity
import com.example.domain.entity.DataNewsRootEntity
import com.example.domain.entity.DataSourceEntity
import kotlinx.parcelize.Parcelize
import org.techtown.data.model.DataArticles
import org.techtown.data.model.DataNewsRootModel
import org.techtown.data.model.DataSource
import org.techtown.presentation.mapper.NewPresentationMapper
import org.techtown.presentation.model.Articles.Companion.fromData
import org.techtown.presentation.model.Articles.Companion.toData
import org.techtown.presentation.model.Source.Companion.fromData
import org.techtown.presentation.model.Source.Companion.toData

data class NewsRootModel(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<Articles> = listOf()
) {
    companion object : NewPresentationMapper<NewsRootModel, DataNewsRootEntity> {
        override fun NewsRootModel.toData(): DataNewsRootEntity {
            return DataNewsRootEntity(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.toData()
                }

            )
        }

        override fun DataNewsRootEntity.fromData(): NewsRootModel {
            return NewsRootModel(
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
data class Source(
    var id: String? = null,
    var name: String? = null
) : Parcelable {
    companion object : NewPresentationMapper<Source, DataSourceEntity> {
        override fun Source.toData(): DataSourceEntity =
            DataSourceEntity(
                id = this.id,
                name = this.name
            )

        override fun DataSourceEntity.fromData(): Source {
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
    companion object : NewPresentationMapper<Articles, DataArticlesEntity> {
        override fun Articles.toData(): DataArticlesEntity =
            DataArticlesEntity(
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

        override fun DataArticlesEntity.fromData(): Articles {
            return Articles(
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