package com.example.presentation.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "newsModel", primaryKeys = ["newsId"])
data class NewsModel(
    @ColumnInfo(name = "newsId") @SerializedName("newsId") var newsId: String,
    @ColumnInfo(name = "password") @SerializedName("password") var password: String
){
}