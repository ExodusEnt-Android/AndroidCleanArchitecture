package org.techtown.presentation.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.techtown.data.model.UserModel

@Parcelize
@Entity(tableName = "UserInfo")
data class PresentationUserModel(
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
) : Parcelable {
    companion object {

        //데이터 모듈 데이터 모델로.
        fun toDataModel(
            presentationUserModel: PresentationUserModel
        ): UserModel {
            return UserModel(
                avatar_url = presentationUserModel.avatar_url,
                events_url = presentationUserModel.events_url,
                following_url = presentationUserModel.following_url,
                followers_url = presentationUserModel.followers_url,
                gists_url = presentationUserModel.gists_url,
                gravatar_id = presentationUserModel.gravatar_id,
                html_url = presentationUserModel.html_url,
                id = presentationUserModel.id,
                login = presentationUserModel.login,
                node_id = presentationUserModel.node_id,
                organizations_url = presentationUserModel.organizations_url,
                received_events_url = presentationUserModel.received_events_url,
                repos_url = presentationUserModel.repos_url,
                score = presentationUserModel.score,
                site_admin = presentationUserModel.site_admin,
                starred_url = presentationUserModel.starred_url,
                subscriptions_url = presentationUserModel.subscriptions_url,
                type = presentationUserModel.type,
                url = presentationUserModel.url,
                is_favorite = presentationUserModel.is_favorite
            )
        }


        //프레젠테이션 모듈 데이터 모델로.
        fun toPresentationModel(
            userModel: UserModel
        ): PresentationUserModel {
            return PresentationUserModel(
                avatar_url = userModel.avatar_url,
                events_url = userModel.events_url,
                following_url = userModel.following_url,
                followers_url = userModel.followers_url,
                gists_url = userModel.gists_url,
                gravatar_id = userModel.gravatar_id,
                html_url = userModel.html_url,
                id = userModel.id,
                login = userModel.login,
                node_id = userModel.node_id,
                organizations_url = userModel.organizations_url,
                received_events_url = userModel.received_events_url,
                repos_url = userModel.repos_url,
                score = userModel.score,
                site_admin = userModel.site_admin,
                starred_url = userModel.starred_url,
                subscriptions_url = userModel.subscriptions_url,
                type = userModel.type,
                url = userModel.url,
                is_favorite = userModel.is_favorite
            )
        }
    }
}
