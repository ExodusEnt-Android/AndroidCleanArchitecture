package com.example.gitsearchbook.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.gitsearchbook.R
import com.example.gitsearchbook.Adapter.UserFragmentAdapter
import com.example.gitsearchbook.GithubService
import com.example.gitsearchbook.Model.GitUserModel
import com.google.gson.JsonArray
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserFragment : Fragment() {

    private lateinit var etSearch : EditText
    private lateinit var btnSearch : Button
    private lateinit var rcvUser : RecyclerView
    private lateinit var userFragmentAdapter : UserFragmentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etSearch = view.findViewById(R.id.et_search)
        btnSearch = view.findViewById(R.id.btn_search)
        rcvUser = view.findViewById(R.id.rcv_user)
        userFragmentAdapter = UserFragmentAdapter(null)
        rcvUser.apply {
            adapter = userFragmentAdapter
        }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/") //어떤 서버로 네트워크 통신을 요청할 것인지에 대한 설정
            .addConverterFactory(GsonConverterFactory.create()) //통신이 완료된 후, 어떤 Converter를 이용하여 데이터를 파싱할 것인지에 대한 설정
            .build()


        val service = retrofit.create(GithubService::class.java)

        service.getUserRepositories("min")?.enqueue(object : Callback<GitUserModel?> {
            override fun onResponse(call: Call<GitUserModel?>?, response: Response<GitUserModel?>?) {
                Log.d("mingue","arrayList 값 :: "+response?.body())
                //어댑터 생성

            }

            override fun onFailure(call: Call<GitUserModel?>?, t: Throwable?) {
                Log.d("mingue","asdasd")
                // Code...
            }
        })

    }


}