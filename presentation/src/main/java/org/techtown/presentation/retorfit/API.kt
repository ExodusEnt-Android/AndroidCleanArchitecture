package org.techtown.presentation.retorfit

import io.reactivex.Observable
import io.reactivex.Single
import org.techtown.presentation.model.UserRootModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("search/users")
    fun getUserInfo(@Query("q") name : String?, @Query("page") page: Int, @Query("per_page") perPage: Int): Single<UserRootModel>
}