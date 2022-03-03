package org.techtown.presentation.datasource.local

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import org.techtown.presentation.db.UserDatabase
import org.techtown.presentation.model.UserModel

class LocalDataSourceImpl(private val userDatabase: UserDatabase?) : LocalDataSource {
    override fun getFavUserInfo(isFavorite: Boolean): Observable<List<UserModel>>? {
        return userDatabase?.userDao()?.getFavUserInfo(isFavorite)
    }

    override fun setFavUserInfo(userModel: UserModel): Completable? =
        userDatabase?.userDao()?.setFavUserInfo(userModel)

    override fun deleteFavUserInfo(id: Int): Completable? =
        userDatabase?.userDao()?.deleteFavUserInfo(id)
}