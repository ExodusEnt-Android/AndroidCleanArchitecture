package org.techtown.presentation.datasource.local

import androidx.lifecycle.LiveData
import org.techtown.presentation.db.UserDatabase
import org.techtown.presentation.model.UserModel

class LocalDataSourceImpl(private val userDatabase: UserDatabase?) : LocalDataSource {
    override fun getFavUserInfo(isFavorite: Boolean): LiveData<List<UserModel>>? {
        return userDatabase?.userDao()?.getFavUserInfo(isFavorite)
    }

    override fun setFavUserInfo(userModel: UserModel) {
        userDatabase?.userDao()?.setFavUserInfo(userModel)
    }

    override fun deleteFavUserInfo(id: Int) {
        userDatabase?.userDao()?.deleteFavUserInfo(id)
    }
}