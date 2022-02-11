package org.techtown.presentation.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserRootModel(
    @SerializedName("incomplete_results") val incomplete_results: Boolean,
    @SerializedName("items") val items: ArrayList<UserModel>,
    @SerializedName("total_count") val total_count: Int
) : Serializable