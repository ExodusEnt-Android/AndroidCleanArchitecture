package org.techtown.presentation.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class UserRootModel(
    @SerializedName("incomplete_results") val incomplete_results: Boolean,
    @SerializedName("items") val items: ArrayList<UserModel>,
    @SerializedName("total_count") val total_count: Int
) : Parcelable