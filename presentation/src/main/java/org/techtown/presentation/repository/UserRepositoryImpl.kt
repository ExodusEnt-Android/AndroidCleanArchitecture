package org.techtown.presentation.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Single
import org.techtown.presentation.datasource.local.LocalDataSource
import org.techtown.presentation.datasource.remote.RemoteDataSource
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.model.UserRootModel

class UserRepositoryImpl(
    private val remoteDatasouce: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    UserRepository {
    override fun getUserInfo(name: String?, page: Int, perPage: Int): Single<UserRootModel> =
        remoteDatasouce.getUserInfo(name, page, perPage)

    override fun getFavUserInfo(isFavorite: Boolean): LiveData<List<UserModel>>? {
        return localDataSource.getFavUserInfo(isFavorite)
    }

    override fun setFavUserInfo(
        userModel: UserModel,
        isFavorite: Boolean,
        callback: (UserModel) -> Unit
    ) {
        localDataSource.setFavUserInfo(userModel, isFavorite)
        callback(userModel) //잘 세팅되었나 확인용.
    }
}