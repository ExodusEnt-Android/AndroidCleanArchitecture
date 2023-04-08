package org.techtown.data.model

import android.os.Parcelable
import com.example.domain.entity.DataArticlesEntity
import com.example.domain.entity.DataNewsRootEntity
import com.example.domain.entity.DataSourceEntity
import kotlinx.parcelize.Parcelize
import org.techtown.data.mapper.NewsDataMapper
import org.techtown.data.model.DataArticles.Companion.fromData
import org.techtown.data.model.DataArticles.Companion.toData
import org.techtown.data.model.DataSource.Companion.fromData
import org.techtown.data.model.DataSource.Companion.toData

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
        override fun DataNewsRootEntity.toData(): DataNewsRootModel {
            return DataNewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.toData()
                }

            )
        }

        override fun DataNewsRootModel.fromData(): DataNewsRootEntity {
            return DataNewsRootEntity(
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
data class DataSource(
    var id: String? = null,
    var name: String? = null
) : Parcelable {
    companion object : NewsDataMapper<DataSourceEntity, DataSource> {
        override fun DataSourceEntity.toData(): DataSource =
            DataSource(
                id = this.id,
                name = this.name
            )


        override fun DataSource.fromData(): DataSourceEntity {
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
        override fun DataArticlesEntity.toData(): DataArticles =
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

        override fun DataArticles.fromData(): DataArticlesEntity {
            return DataArticlesEntity(
                source = this.source?.fromData(),
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