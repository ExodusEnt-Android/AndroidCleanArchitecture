package org.techtown.presentation.datasource.local

import androidx.lifecycle.LiveData
import org.techtown.presentation.db.UserDatabase
import org.techtown.presentation.model.UserModel

class LocalDataSourceImpl(private val userDatabase: UserDatabase?) : LocalDataSource {
    override fun getFavUserInfo(isFavorite: Boolean): LiveData<List<UserModel>>? {
        return userDatabase?.userDao()?.getFavUserInfo(isFavorite)
    }

    override fun setFavUserInfo(userModel: UserModel, isFavorite: Boolean) {
        userDatabase?.userDao()?.setFavUserInfo(userModel)
    }
}