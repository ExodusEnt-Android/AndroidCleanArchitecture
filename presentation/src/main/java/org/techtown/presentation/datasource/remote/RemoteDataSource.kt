package org.techtown.presentation.datasource.remote

import io.reactivex.rxjava3.core.Single
import org.techtown.presentation.model.UserRootModel

interface RemoteDataSource {
    fun getUserInfo(name: String?, page: Int, perPage: Int): Single<UserRootModel>
}