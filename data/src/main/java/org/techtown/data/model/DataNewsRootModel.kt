package org.techtown.data.model

import android.os.Parcelable
import com.example.domain.entity.DataArticlesEntity
import com.example.domain.entity.DataNewsRootEntity
import com.example.domain.entity.DataSourceEntity
import kotlinx.parcelize.Parcelize
import org.techtown.data.mapper.NewsDataMapper
import org.techtown.data.model.DataArticles.Companion.fromEntity
import org.techtown.data.model.DataArticles.Companion.toEntity
import org.techtown.data.model.DataSource.Companion.fromEntity
import org.techtown.data.model.DataSource.Companion.toEntity

/**
 * @see
 * */

@Parcelize
data class DataNewsRootModel(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<DataArticles> = listOf()
) : Parcelable {
    companion object : NewsDataMapper<DataNewsRootEntity, DataNewsRootModel> {
        override fun DataNewsRootEntity.toEntity(): DataNewsRootModel {
            return DataNewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.toEntity()
                }

            )
        }

        override fun DataNewsRootModel.fromEntity(): DataNewsRootEntity {
            return DataNewsRootEntity(
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
data class DataSource(
    var id: String? = null,
    var name: String? = null
) : Parcelable {
    companion object : NewsDataMapper<DataSourceEntity, DataSource> {
        override fun DataSourceEntity.toEntity(): DataSource =
            DataSource(
                id = this.id,
                name = this.name
            )


        override fun DataSource.fromEntity(): DataSourceEntity {
            return DataSourceEntity(
                id = this.id,
                name = this.name
            )
        }

    }
}

@Parcelize
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
) : Parcelable {
    companion object : NewsDataMapper<DataArticlesEntity, DataArticles> {
        override fun DataArticlesEntity.toEntity(): DataArticles =
            DataArticles(
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

        override fun DataArticles.fromEntity(): DataArticlesEntity {
            return DataArticlesEntity(
                source = this.source?.fromEntity(),
                author = this.author,
                title = this.title,
                description = this.description,
                url = this.url ?: "",
                urlToImage = this.urlToImage,
                publishedAt = this.publishedAt,
                content = this.content,
                isLoading = this.isLoading
            )
        }

    }
}