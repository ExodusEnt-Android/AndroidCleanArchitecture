package org.techtown.presentation.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class NewsRootModel(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: ArrayList<Articles> = arrayListOf()
)

@Parcelize
data class Source(
    @ColumnInfo(name = "id") var id: String? = null,
    @ColumnInfo(name = "name") var name: String? = null
) : Parcelable

@Entity(
    tableName = "articles"
)
@Parcelize
data class Articles(
    @Embedded var source: Source? = Source(),
    @ColumnInfo(name = "author") var author: String? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String? = null,
    @PrimaryKey @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "urlToImage") var urlToImage: String? = null,
    @ColumnInfo(name = "publishedAt") var publishedAt: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
    var isLoading: Boolean = false,
    ) : Parcelable