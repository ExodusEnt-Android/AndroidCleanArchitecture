package com.example.presentation.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OwnerModel(
    val login : String,
    val avatar_url : String,
    val html_url : String
): Parcelable