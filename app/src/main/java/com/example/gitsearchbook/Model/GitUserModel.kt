package com.example.gitsearchbook.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GitUserModel(
    val id : Int,
    val name : String
):Parcelable