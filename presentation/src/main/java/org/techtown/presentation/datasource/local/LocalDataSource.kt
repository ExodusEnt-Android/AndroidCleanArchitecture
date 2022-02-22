package org.techtown.presentation.datasource.local

import androidx.lifecycle.LiveData
import org.techtown.presentation.model.UserModel

interface LocalDataSource {

    //즐겨찾기 유저 목록을 가져옴.
    fun getFavUserInfo(isFavorite: Boolean): LiveData<List<UserModel>>?

    //즐겨찾기 유저 목록 세팅.
    fun setFavUserInfo(userModel: UserModel, isFavorite: Boolean)
}