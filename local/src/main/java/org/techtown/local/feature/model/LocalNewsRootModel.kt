package org.techtown.local.feature.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.techtown.data.model.DataArticles
import org.techtown.data.model.DataNewsRootModel
import org.techtown.data.model.DataSource
import org.techtown.local.feature.mapper.NewsLocalMapper
import org.techtown.local.feature.model.LocalArticles.Companion.fromData
import org.techtown.local.feature.model.LocalArticles.Companion.toData
import org.techtown.local.feature.model.LocalSource.Companion.fromData
import org.techtown.local.feature.model.LocalSource.Companion.toData


/**
 * @see
 * */

@Parcelize
data class LocalNewsRootModel(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<LocalArticles> = listOf()
) : Parcelable {

    companion object : NewsLocalMapper<LocalNewsRootModel, DataNewsRootModel> {
        override fun LocalNewsRootModel.toData(): DataNewsRootModel {
            return DataNewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.toData()
                }

            )
        }

        override fun DataNewsRootModel.fromData(): LocalNewsRootModel {
            return LocalNewsRootModel(
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
data class LocalSource(
    @ColumnInfo(name = "id") var id: String? = null,
    @ColumnInfo(name = "name") var name: String? = null
) : Parcelable {
    companion object : NewsLocalMapper<LocalSource, DataSource> {
        override fun LocalSource.toData(): DataSource =
            DataSource(
                id = this.id,
                name = this.name
            )


        override fun DataSource.fromData(): LocalSource {
            return LocalSource(
                id = this.id,
                name = this.name
            )
        }

    }
}

@Entity(
    tableName = "articles"
)
@Parcelize
data class LocalArticles(
    @Embedded var source: LocalSource? = null,
    @ColumnInfo(name = "author") var author: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @PrimaryKey @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "urlToImage") var urlToImage: String? = null,
    @ColumnInfo(name = "publishedAt") var publishedAt: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
    @ColumnInfo(name = "isLoading") var isLoading: Boolean = false,
) : Parcelable {
    companion object : NewsLocalMapper<LocalArticles, DataArticles> {
        override fun LocalArticles.toData(): DataArticles =
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

        override fun DataArticles.fromData(): LocalArticles {
            return LocalArticles(
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