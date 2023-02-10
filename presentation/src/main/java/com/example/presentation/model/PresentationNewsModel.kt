/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.data.model.Articles
import com.example.presentation.mapper.PresentationMapper
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PresentationArticles(
    @PrimaryKey @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "author") var author: String? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "urlToImage") var urlToImage: String? = null,
    @ColumnInfo(name = "publishedAt")  var publishedAt: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
) : Parcelable {

    companion object : PresentationMapper<PresentationArticles, Articles> {
        override fun PresentationArticles.toData(): Articles =
            Articles(
                url = this.url,
                author = this.author,
                title = this.title,
                description = this.description,
                urlToImage = this.urlToImage,
                publishedAt = this.publishedAt,
                content = this.content
            )

        override fun Articles.fromData(): PresentationArticles {
            return PresentationArticles(
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