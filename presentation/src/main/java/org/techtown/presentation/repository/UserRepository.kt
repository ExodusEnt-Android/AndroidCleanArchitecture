package org.techtown.presentation.repository

import io.reactivex.rxjava3.core.Single
import org.techtown.presentation.model.UserRootModel


interface UserRepository {
    fun getUserInfo(name: String?, page: Int, perPage: Int): Single<UserRootModel>
}