package org.techtown.presentation.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "login"
)
@Parcelize
data class LoginModel(
    @PrimaryKey @ColumnInfo(name = "user_id") var user_id : String,
    @ColumnInfo(name = "isLogined") var isLogined: Boolean = false
) : Parcelable
