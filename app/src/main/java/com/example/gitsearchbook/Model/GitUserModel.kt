package com.example.gitsearchbook.Model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GitUserModel (
    val total_count : Int,
    val items : ArrayList<UserItemModel>
):Parcelable
