/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.data.model.ArticlesDataModel
import com.example.data.model.DataNewsModel
import com.example.local.model.RemoteArticles.Companion.fromData
import com.example.local.model.RemoteArticles.Companion.toData
import com.example.remote.mapper.RemoteMapper

import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemoteNewsModel constructor(
    var articles: List<RemoteArticles> = listOf()
):Parcelable{
    companion object : RemoteMapper<RemoteNewsModel, DataNewsModel> {
        override fun RemoteNewsModel.toData(): DataNewsModel {
            return DataNewsModel(
                dataArticlesModelDataModel = this.articles.map {
                    it.toData()
                }

            )
        }

        override fun DataNewsModel.fromData(): RemoteNewsModel {
            return RemoteNewsModel(
                articles = this.dataArticlesModelDataModel.map {
                    it.fromData()
                }
            )
        }
    }
}

@Parcelize
data class RemoteArticles(
    @PrimaryKey @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "author") var author: String? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "urlToImage") var urlToImage: String? = null,
    @ColumnInfo(name = "publishedAt")  var publishedAt: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
) : Parcelable{

    companion object : RemoteMapper<RemoteArticles, ArticlesDataModel> {
        override fun RemoteArticles.toData(): ArticlesDataModel =
            ArticlesDataModel(
                url = this.url,
                author = this.author,
                title = this.title,
                description = this.description,
                urlToImage = this.urlToImage,
                publishedAt = this.publishedAt,
                content = this.content
            )

        override fun ArticlesDataModel.fromData(): RemoteArticles {
            return RemoteArticles(
                url = this.url,
                author = this.author,
                title = this.title,
                description = this.description,
                urlToImage = this.urlToImage,
                publishedAt = this.publishedAt,
                content = this.content
            )
        }

    }
}