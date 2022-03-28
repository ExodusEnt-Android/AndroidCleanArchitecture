package org.techtown.data.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "UserInfo")
data class UserModel(
    @ColumnInfo(name = "avatar_url") val avatar_url: String,
    @ColumnInfo(name = "events_url") val events_url: String,
    @ColumnInfo(name = "followers_url") val followers_url: String,
    @ColumnInfo(name = "following_url") val following_url: String,
    @ColumnInfo(name = "gists_url") val gists_url: String,
    @ColumnInfo(name = "gravatar_id") val gravatar_id: String,
    @ColumnInfo(name = "html_url") val html_url: String,
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "node_id") val node_id: String,
    @ColumnInfo(name = "organizations_url") val organizations_url: String,
    @ColumnInfo(name = "received_events_url") val received_events_url: String,
    @ColumnInfo(name = "repos_url") val repos_url: String,
    @ColumnInfo(name = "score") val score: Double,
    @ColumnInfo(name = "site_admin") val site_admin: Boolean,
    @ColumnInfo(name = "starred_url") val starred_url: String,
    @ColumnInfo(name = "subscriptions_url") val subscriptions_url: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "is_favorite") var is_favorite: Boolean = false
) : Parcelable
