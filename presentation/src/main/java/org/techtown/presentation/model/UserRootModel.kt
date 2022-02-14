package org.techtown.presentation.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class UserRootModel(
    val incomplete_results: Boolean,
    val items: ArrayList<UserModel>,
    val total_count: Int
) : Parcelable