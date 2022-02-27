package com.example.gitsearchbook.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
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
import java.util.logging.Logger

class UserFragment : Fragment() {

    private lateinit var etSearch : EditText
    private lateinit var tvEmpty : TextView
    private lateinit var btnSearch : Button
    private lateinit var rcvUser : RecyclerView
    private lateinit var userFragmentAdapter : UserFragmentAdapter
    private lateinit var mGlideRequestManager: RequestManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etSearch = view.findViewById(R.id.et_search)
        tvEmpty = view.findViewById(R.id.tv_empty)
        btnSearch = view.findViewById(R.id.btn_search)
        rcvUser = view.findViewById(R.id.rcv_user)
        mGlideRequestManager = Glide.with(this)
        userFragmentAdapter = UserFragmentAdapter(mGlideRequestManager)
        rcvUser.apply {
            adapter = userFragmentAdapter
        }

        clickEvent()

        btnSearch.setOnClickListener {  //검색버튼 누르면 유저 정보 부르는 api 호출
            getUserInfo(etSearch.text.toString())
        }
    }

    fun getUserInfo(userName : String): ArrayList<GitUserModel>? {
        var gitUserName : ArrayList<GitUserModel>? = ArrayList<GitUserModel>()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/") //어떤 서버로 네트워크 통신을 요청할 것인지에 대한 설정
            .addConverterFactory(GsonConverterFactory.create()) //통신이 완료된 후, 어떤 Converter를 이용하여 데이터를 파싱할 것인지에 대한 설정
            .build()

        val service = retrofit.create(GithubService::class.java)

        service.getUserRepositories(userName).enqueue(object : Callback<ArrayList<GitUserModel>> {
            override fun onResponse(call: Call<ArrayList<GitUserModel>>, response: Response<ArrayList<GitUserModel>>) {
                Log.d("mingue","response site::"+response)

                Log.d("mingue","arrayList 값 :: "+response.body())
                gitUserName = response.body()

                if(gitUserName.isNullOrEmpty()){    //검색된 유저가 없을 경우
                    tvEmpty.visibility = View.VISIBLE
                }
                else{                               //검색된 유저가 있을 경우
                    tvEmpty.visibility = View.GONE
                   userFragmentAdapter.setGitUserArray(gitUserName!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<GitUserModel>> , t: Throwable?) {
                Log.d("mingue","asdasd"+t?.message)
            }
        })
        return gitUserName
    }

    //즐겨찾기 할 때 쓸 계획
    private fun clickEvent() {
        userFragmentAdapter.setOnItemClickListener(object : UserFragmentAdapter.OnItemClickListener{

        })

    }


}