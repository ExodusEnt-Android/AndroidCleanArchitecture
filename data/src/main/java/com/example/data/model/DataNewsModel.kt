/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class DataNewsModel(
    var articles: List<Articles> = listOf()
)

@Parcelize
data class Articles(
    var url: String,
    var author: String? = null,
    var title: String,
    var description: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null,
) : Parcelable