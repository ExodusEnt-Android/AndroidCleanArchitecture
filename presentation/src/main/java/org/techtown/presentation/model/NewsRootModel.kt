package org.techtown.presentation.model

import android.os.Parcelable
import com.example.domain.entity.DataArticlesEntity
import com.example.domain.entity.DataNewsRootEntity
import com.example.domain.entity.DataSourceEntity
import kotlinx.parcelize.Parcelize
import org.techtown.presentation.mapper.NewPresentationMapper
import org.techtown.presentation.model.Articles.Companion.fromEntity
import org.techtown.presentation.model.Articles.Companion.toEntity
import org.techtown.presentation.model.Source.Companion.fromEntity
import org.techtown.presentation.model.Source.Companion.toEntity

data class NewsRootModel(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<Articles> = listOf()
) {
    companion object : NewPresentationMapper<NewsRootModel, DataNewsRootEntity> {
        override fun NewsRootModel.toEntity(): DataNewsRootEntity {
            return DataNewsRootEntity(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.toEntity()
                }

            )
        }

        override fun DataNewsRootEntity.fromEntity(): NewsRootModel {
            return NewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.fromEntity()
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
        override fun Source.toEntity(): DataSourceEntity =
            DataSourceEntity(
                id = this.id,
                name = this.name
            )

        override fun DataSourceEntity.fromEntity(): Source {
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
        override fun Articles.toEntity(): DataArticlesEntity =
            DataArticlesEntity(
                source = this.source?.toEntity(),
                author = this.author,
                title = this.title,
                description = this.description,
                url = this.url,
                urlToImage = this.urlToImage,
                publishedAt = this.publishedAt,
                content = this.content,
                isLoading = this.isLoading
            )

        override fun DataArticlesEntity.fromEntity(): Articles {
            return Articles(
                source = this.source?.fromEntity(),
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