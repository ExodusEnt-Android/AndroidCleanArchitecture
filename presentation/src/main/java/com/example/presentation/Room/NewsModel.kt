package com.example.presentation.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "newsModel", primaryKeys = ["id"])
data class NewsModel(
    @ColumnInfo(name = "id") @SerializedName("id") var id: String,
    @ColumnInfo(name = "name") @SerializedName("name") var name : String,
    @ColumnInfo(name = "author") @SerializedName("author") var author : String,
    @ColumnInfo(name = "title") @SerializedName("title") var title : String,
    @ColumnInfo(name = "description") @SerializedName("description") var description : String,
    @ColumnInfo(name = "urlToImage") @SerializedName("urlToImage") var urlToImage : String,
){
}