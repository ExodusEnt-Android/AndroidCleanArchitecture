import com.example.presentation.BuildConfig
import com.example.presentation.model.Article
import com.example.presentation.model.BaseDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //top-headlines ->  뉴스 리스트 가져오기
    @GET("/v2/top-headlines")
    fun getTopHeadLines(
        @Query("country") country: String = "kr",//한국으로 고정
        @Query("category") category: String? = null,//optional
    ): Call<BaseDataModel<Article>>

}