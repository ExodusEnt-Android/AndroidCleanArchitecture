package com.example.gitsearchbook.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gitsearchbook.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/") //어떤 서버로 네트워크 통신을 요청할 것인지에 대한 설정
            .addConverterFactory(GsonConverterFactory.create()) //통신이 완료된 후, 어떤 Converter를 이용하여 데이터를 파싱할 것인지에 대한 설정
            .build()


//        val service = retrofit.create(GithubService::class.java)
//        val request: Call<GitUserModel?>? = service.getUserRepositories("mingue")
//        request?.enqueue(object : Callback<GitUserModel?> {
//            override fun onResponse(call: Call<GitUserModel?>?, response: Response<GitUserModel?>?) {
//                Log.d("mingue","arrayList 값 :: "+response)
//
//
//                // Code...
//            }
//
//            override fun onFailure(call: Call<GitUserModel?>?, t: Throwable?) {
//                // Code...
//            }
//        })
    }
}