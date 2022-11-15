import com.example.presentation.model.Article
import com.example.presentation.model.BaseDataModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //top-headlines ->  뉴스 리스트 가져오기
    @GET("/v2/top-headlines")
    fun getTopHeadLines(
        @Query("country") country: String = "us",//미국으로 고정
        @Query("category") category: String? = null,//optional
        @Query("page") page:Int,
        @Query("pageSize")pageSize:Int
    ): Single<BaseDataModel<Article>>

}