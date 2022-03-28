package org.techtown.data.retrofit

import io.reactivex.rxjava3.core.Single
import org.techtown.data.model.UserRootModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("search/users")
    fun getUserInfo(@Query("q") name : String?, @Query("page") page: Int, @Query("per_page") perPage: Int): Single<Response<UserRootModel>>
}