package org.techtown.presentation.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.techtown.presentation.datasource.local.LocalDataSource
import org.techtown.presentation.datasource.remote.RemoteDataSource
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.model.UserRootModel
import retrofit2.Response

class UserRepositoryImpl(
    private val remoteDatasouce: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    UserRepository {
    override fun getUserInfo(name: String?, page: Int, perPage: Int): Single<Response<UserRootModel>> =
        remoteDatasouce.getUserInfo(name, page, perPage)

    override fun getFavUserInfo(isFavorite: Boolean): Observable<List<UserModel>>? {
        return localDataSource.getFavUserInfo(isFavorite)
    }

    override fun setFavUserInfo(
        userModel: UserModel,
        callback: (UserModel) -> Unit
    ) {
        localDataSource.setFavUserInfo(userModel)
        callback(userModel) //잘 세팅되었나 확인용.
    }

    override fun deleteFavUserInfo(id: Int, callback: (Int) -> Unit) {
        localDataSource.deleteFavUserInfo(id)
        callback(id)
    }
}