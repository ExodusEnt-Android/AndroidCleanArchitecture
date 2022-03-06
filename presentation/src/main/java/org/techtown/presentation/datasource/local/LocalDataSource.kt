package org.techtown.presentation.datasource.local

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.techtown.presentation.model.UserModel

interface LocalDataSource {

    //즐겨찾기 유저 목록을 가져옴.
    fun getFavUserInfo(isFavorite: Boolean): Single<List<UserModel>>?

    //즐겨찾기 유저 목록 세팅.
    fun setFavUserInfo(userModel: UserModel): Completable?

    //즐겨찾기 유저 목록 삭제.
    fun deleteFavUserInfo(id: Int): Completable?
}