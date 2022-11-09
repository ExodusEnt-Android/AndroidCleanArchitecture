package com.example.presentation

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class NewsData(
    @SerializedName("articles") var articles: ArrayList<Articles> = arrayListOf()
)

@Entity(tableName = "articles")
@Parcelize
data class Articles(
    @PrimaryKey @ColumnInfo(name = "url") @SerializedName("url") var url: String,
    @ColumnInfo(name = "author") @SerializedName("author") var author: String? = null,
    @ColumnInfo(name = "title") @SerializedName("title") var title: String,
    @ColumnInfo(name = "description") @SerializedName("description") var description: String? = null,
    @ColumnInfo(name = "urlToImage") @SerializedName("urlToImage") var urlToImage: String? = null,
    @ColumnInfo(name = "publishedAt") @SerializedName("publishedAt") var publishedAt: String? = null,
    @ColumnInfo(name = "content") @SerializedName("content") var content: String? = null,
) : Parcelable