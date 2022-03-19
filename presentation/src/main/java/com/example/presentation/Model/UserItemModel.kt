package com.example.presentation.Model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItemModel (
    val login : String,
    val avatar_url : String
):Parcelable