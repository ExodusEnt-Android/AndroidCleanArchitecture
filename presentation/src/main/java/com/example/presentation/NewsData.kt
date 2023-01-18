package com.example.presentation

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class NewsData(
    var articles: ArrayList<Articles> = arrayListOf()
)

@Entity(tableName = "articles")
@Parcelize
data class Articles(
    @PrimaryKey @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "author") var author: String? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "urlToImage") var urlToImage: String? = null,
    @ColumnInfo(name = "publishedAt")  var publishedAt: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
) : Parcelable