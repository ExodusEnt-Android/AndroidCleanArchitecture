package org.techtown.data.datasource.remote

import io.reactivex.rxjava3.core.Single
import org.techtown.data.model.UserRootModel
import retrofit2.Response

interface RemoteDataSource {
    fun getUserInfo(name: String?, page: Int, perPage: Int): Single<Response<UserRootModel>>
}