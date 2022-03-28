package org.techtown.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRootModel(
    val incomplete_results: Boolean,
    val items: ArrayList<UserModel>,
    val total_count: Int
) : Parcelable
