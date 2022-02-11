package org.techtown.presentation.model

import com.google.gson.annotations.SerializedName

data class UserRootModel(
    @SerializedName("incomplete_results") val incomplete_results: Boolean,
    @SerializedName("items") val items: List<UserModel>,
    @SerializedName("total_count") val total_count: Int
)