package com.example.gitsearchbook.Model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItemModel (
    val avatar_url : String
):Parcelable