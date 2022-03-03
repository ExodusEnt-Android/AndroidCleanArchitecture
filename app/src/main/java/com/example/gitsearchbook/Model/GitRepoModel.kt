package com.example.gitsearchbook.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GitRepoModel(
    val id : Int,
    val name : String,
    val owner : OwnerModel
):Parcelable
