package com.example.presentation.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserInfo", primaryKeys = ["email"])
data class UserModel(
    @ColumnInfo(name = "email") @SerializedName("email") var email: String,
    @ColumnInfo(name = "password") @SerializedName("password") var password: String
){
}