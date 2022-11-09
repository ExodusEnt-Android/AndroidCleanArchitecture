package com.example.presentation

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "login")
@Parcelize
data class LoginData (
    @PrimaryKey @ColumnInfo(name = "email") @SerializedName("email") var email: String,
    @ColumnInfo(name = "pwd") @SerializedName("pwd") var pwd: String
):Parcelable
