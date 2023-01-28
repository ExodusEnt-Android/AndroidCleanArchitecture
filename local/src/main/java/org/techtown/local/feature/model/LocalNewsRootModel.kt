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
import org.techtown.local.feature.mapper.FloLocalMapper
import org.techtown.local.feature.model.LocalArticles.Companion.fromFloData
import org.techtown.local.feature.model.LocalArticles.Companion.toFloData
import org.techtown.local.feature.model.LocalSource.Companion.fromFloData
import org.techtown.local.feature.model.LocalSource.Companion.toFloData


/**
 * @see
 * */

@Parcelize
data class LocalNewsRootModel(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<LocalArticles> = listOf()
) : Parcelable {

    companion object : FloLocalMapper<LocalNewsRootModel, DataNewsRootModel> {
        override fun LocalNewsRootModel.toFloData(): DataNewsRootModel {
            return DataNewsRootModel(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles.map {
                    it.toFloData()
                }

            )
        }

        override fun DataNewsRootModel.fromFloData(): LocalNewsRootModel {
            return LocalNewsRootModel(
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
data class LocalSource(
    @ColumnInfo(name = "id") var id: String? = null,
    @ColumnInfo(name = "name") var name: String? = null
) : Parcelable {
    companion object : FloLocalMapper<LocalSource, DataSource> {
        override fun LocalSource.toFloData(): DataSource =
            DataSource(
                id = this.id,
                name = this.name
            )


        override fun DataSource.fromFloData(): LocalSource {
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
    companion object : FloLocalMapper<LocalArticles, DataArticles> {
        override fun LocalArticles.toFloData(): DataArticles =
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

        override fun DataArticles.fromFloData(): LocalArticles {
            return LocalArticles(
                source = this.source?.fromFloData(),
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