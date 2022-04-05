package org.techtown.data.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.techtown.data.model.UserModel
import org.techtown.data.model.UserRootModel
import retrofit2.Response

interface UserRepository {
    fun getUserInfo(name: String?, page: Int, perPage: Int): Single<Response<UserRootModel>>

    fun getFavUserInfo(isFavorite: Boolean): Single<List<UserModel>>?

    fun setFavUserInfo(userModel: UserModel): Completable?

    fun deleteFavUserInfo(model: UserModel): Completable?
}