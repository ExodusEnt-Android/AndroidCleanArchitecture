package org.techtown.presentation.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Completable
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

    override fun setFavUserInfo(userModel: UserModel): Completable? =
        localDataSource.setFavUserInfo(userModel)

    override fun deleteFavUserInfo(id: Int): Completable? =
        localDataSource.deleteFavUserInfo(id)
}