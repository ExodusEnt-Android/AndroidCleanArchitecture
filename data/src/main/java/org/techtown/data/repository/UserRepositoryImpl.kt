package org.techtown.data.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.techtown.data.datasource.local.LocalDataSource
import org.techtown.data.datasource.remote.RemoteDataSource
import org.techtown.data.model.UserModel
import org.techtown.data.model.UserRootModel
import retrofit2.Response

class UserRepositoryImpl(
    private val remoteDatasouce: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : UserRepository {
    override fun getUserInfo(name: String?, page: Int, perPage: Int): Single<Response<UserRootModel>> =
        remoteDatasouce.getUserInfo(name, page, perPage)

    override fun getFavUserInfo(isFavorite: Boolean): Single<List<UserModel>>? {
        return localDataSource.getFavUserInfo(isFavorite)
    }

    override fun setFavUserInfo(userModel: UserModel): Completable? =
        localDataSource.setFavUserInfo(userModel)

    override fun deleteFavUserInfo(model: UserModel): Completable? =
        localDataSource.deleteFavUserInfo(model)
}