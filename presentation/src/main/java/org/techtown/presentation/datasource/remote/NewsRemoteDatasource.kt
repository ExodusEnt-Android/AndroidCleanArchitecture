

package org.techtown.presentation.datasource.remote

import org.techtown.presentation.model.NewsRootModel
import retrofit2.Call
import retrofit2.Callback


/**
 * @see
 * */

interface NewsRemoteDatasource {
    fun getTopHeadlinesArticles(
        country: String,
        category: String?,
        pageSize: Int,
        offset: Int
    ): Call<NewsRootModel>
}