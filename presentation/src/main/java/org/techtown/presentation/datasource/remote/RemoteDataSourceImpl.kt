package org.techtown.presentation.datasource.remote

import io.reactivex.rxjava3.core.Single
import org.techtown.presentation.model.UserRootModel
import org.techtown.presentation.retorfit.API

class RemoteDataSourceImpl(val api: API) : RemoteDataSource {
    override fun getUserInfo(name: String?, page: Int, perPage: Int): Single<UserRootModel> =
        api.getUserInfo(name, page, perPage)
}