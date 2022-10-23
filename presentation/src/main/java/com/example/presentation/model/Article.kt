package com.example.presentation.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "newsArticleTable"
)
//뉴스 기사용 데이터 모델
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true) var uid:Long,
    @ColumnInfo(name = "author") val author: String? = "",
    @ColumnInfo(name = "content") val content: String? = "",
    @ColumnInfo(name = "description") val description: String? = "",
    @ColumnInfo(name = "publishedAt") val publishedAt: String? = "",
    @ColumnInfo(name = "title") val title: String? = "",
    @ColumnInfo(name = "url") val url: String? = "",
    @ColumnInfo(name = "urlToImage") val urlToImage: String? = ""
):Parcelable