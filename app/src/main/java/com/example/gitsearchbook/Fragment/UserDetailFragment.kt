package com.example.gitsearchbook.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.gitsearchbook.R
import com.example.gitsearchbook.Adapter.UserDetailFragmentAdapter
import com.example.gitsearchbook.Model.GitRepoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailFragment : Fragment() {

    private lateinit var etSearch : EditText
    private lateinit var tvEmpty : TextView
    private lateinit var btnSearch : Button
    private lateinit var rcvUser : RecyclerView
    private lateinit var userDetailFragmentAdapter : UserDetailFragmentAdapter
    private lateinit var mGlideRequestManager: RequestManager

    private var count = 0


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
        userDetailFragmentAdapter = UserDetailFragmentAdapter(mGlideRequestManager)
        rcvUser.apply {
            adapter = userDetailFragmentAdapter
        }

        clickEvent()
        btnSearch.setOnClickListener {  //검색버튼 누르면 유저 정보 부르는 api 호출
            getUserInfo(etSearch.text.toString())
        }
    }

    fun getUserInfo(userName : String): ArrayList<GitRepoModel>? {
        var gitRepoName : ArrayList<GitRepoModel>? = ArrayList<GitRepoModel>()


        GitRetrofit.repoService.getUserRepositories(userName).enqueue(object : Callback<ArrayList<GitRepoModel>> {
            override fun onResponse(call: Call<ArrayList<GitRepoModel>>, response: Response<ArrayList<GitRepoModel>>) {
                Log.d("mingue","arrayList 값 :: "+response.body())
                gitRepoName = response.body()

                if(gitRepoName.isNullOrEmpty()){    //검색된 유저가 없을 경우
                    tvEmpty.visibility = View.VISIBLE
                }
                else{                               //검색된 유저가 있을 경우
                    tvEmpty.visibility = View.GONE
                   userDetailFragmentAdapter.setGitUserArray(gitRepoName!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<GitRepoModel>>, t: Throwable?) {
                count = 1
                getUserInfoFail(count)
            }
        })
        return gitRepoName
    }

    //즐겨찾기 할 때 쓸 계획
    private fun clickEvent() {
        userDetailFragmentAdapter.setOnItemClickListener(object : UserDetailFragmentAdapter.OnItemClickListener{
            override fun clickUserLike(gitRepoModel: GitRepoModel) {
                Log.d("click","UserModel::"+gitRepoModel)
            }

        })

    }

    private fun getUserInfoFail(count: Int){
        if(count==1) {
            Log.d("asdasd:", "asdasd:" + count)
            getUserInfo(etSearch.text.toString())
            this.count = 0
        }
    }


}