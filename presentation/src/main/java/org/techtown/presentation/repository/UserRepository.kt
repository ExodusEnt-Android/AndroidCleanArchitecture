package org.techtown.presentation.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.model.UserRootModel
import retrofit2.Response


interface UserRepository {
    fun getUserInfo(name: String?, page: Int, perPage: Int): Single<Response<UserRootModel>>

    fun getFavUserInfo(isFavorite: Boolean): Observable<List<UserModel>>?

    fun setFavUserInfo(userModel: UserModel, callback: (UserModel) -> Unit)

    fun deleteFavUserInfo(id: Int, callback: (Int) -> Unit)
}