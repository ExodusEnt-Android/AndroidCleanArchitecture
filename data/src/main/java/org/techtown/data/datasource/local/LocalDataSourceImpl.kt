package org.techtown.data.datasource.local

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.techtown.data.model.UserModel
import org.techtown.data.room.UserDatabase

class LocalDataSourceImpl(private val userDatabase: UserDatabase?) : LocalDataSource {
    override fun getFavUserInfo(isFavorite: Boolean): Single<List<UserModel>>? {
        return userDatabase?.userDao()?.getFavUserInfo(isFavorite)
    }

    override fun setFavUserInfo(userModel: UserModel): Completable? =
        userDatabase?.userDao()?.setFavUserInfo(userModel)

    override fun deleteFavUserInfo(model: UserModel): Completable? =
        userDatabase?.userDao()?.deleteFavUserInfo(model.id)
}