package org.techtown.presentation.datasource.local

import io.reactivex.rxjava3.core.Observable
import org.techtown.presentation.model.UserModel

interface LocalDataSource {

    //즐겨찾기 유저 목록을 가져옴.
    fun getFavUserInfo(isFavorite: Boolean): Observable<List<UserModel>>?

    //즐겨찾기 유저 목록 세팅.
    fun setFavUserInfo(userModel: UserModel)

    //즐겨찾기 유저 목록 삭제.
    fun deleteFavUserInfo(id: Int)
}