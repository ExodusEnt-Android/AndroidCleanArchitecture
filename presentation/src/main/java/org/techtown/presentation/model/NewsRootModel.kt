package org.techtown.presentation.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class NewsRootModel(
    @SerializedName("status") var status: String? = null,
    @SerializedName("totalResults") var totalResults: Int? = null,
    @SerializedName("articles") var articles: ArrayList<Articles> = arrayListOf()
)

@Parcelize
data class Source(
    @ColumnInfo(name = "id") @SerializedName("id") var id: String? = null,
    @ColumnInfo(name = "name") @SerializedName("name") var name: String? = null
) : Parcelable

@Entity(
    tableName = "articles"
)
@Parcelize
data class Articles(
    @Embedded @SerializedName("source") var source: Source? = Source(),
    @ColumnInfo(name = "author") @SerializedName("author") var author: String? = null,
    @ColumnInfo(name = "title") @SerializedName("title") var title: String,
    @ColumnInfo(name = "description") @SerializedName("description") var description: String? = null,
    @PrimaryKey @ColumnInfo(name = "url") @SerializedName("url") var url: String,
    @ColumnInfo(name = "urlToImage") @SerializedName("urlToImage") var urlToImage: String? = null,
    @ColumnInfo(name = "publishedAt") @SerializedName("publishedAt") var publishedAt: String? = null,
    @ColumnInfo(name = "content") @SerializedName("content") var content: String? = null,
    var isLoading: Boolean = false,
    ) : Parcelable