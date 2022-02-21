package org.techtown.presentation.repository

import io.reactivex.rxjava3.core.Single
import org.techtown.presentation.datasource.remote.RemoteDataSource
import org.techtown.presentation.model.UserRootModel

class UserRepositoryImpl(val datasouce: RemoteDataSource) : UserRepository {
    override fun getUserInfo(name: String?, page: Int, perPage: Int): Single<UserRootModel> =
        datasouce.getUserInfo(name, page, perPage)
}