package com.example.gitsearchbook.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.gitsearchbook.Activity.MainActivity
import com.example.gitsearchbook.Adapter.UserSearchFragmentAdapter
import com.example.gitsearchbook.Model.GitUserModel
import com.example.gitsearchbook.databinding.FragmentUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserSearchFragment : Fragment(){

    private var binding : FragmentUserBinding? = null
    private var gitUserModel : GitUserModel? = null
    private lateinit var mGlideRequestManager: RequestManager
    private lateinit var userSearchFragmentAdapter: UserSearchFragmentAdapter

    private var count = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gitUserModel = arguments?.getParcelable(MainActivity.PARAM_USER)
        mGlideRequestManager = Glide.with(this)

         binding?.etSearch?.setText("mingue")
        userSearchFragmentAdapter = UserSearchFragmentAdapter(mGlideRequestManager)
        binding?.rcvUser?.apply {
            adapter = userSearchFragmentAdapter
        }
        userSearchFragmentAdapter.setGitUser(gitUserModel!!)

    }

    fun getUserInfo(userName : String): GitUserModel? {
        var gitRepoName : GitUserModel? = null

        GitRetrofit.userService.getUserName(userName,1,10).enqueue(object : Callback<GitUserModel> {
            override fun onResponse(call: Call<GitUserModel>, response: Response<GitUserModel>) {
                gitRepoName = response.body()!!
                if(gitRepoName == null){    //검색된 유저가 없을 경우
                    binding?.tvEmpty?.visibility = View.VISIBLE
                }
                else{                               //검색된 유저가 있을 경우
                    binding?.tvEmpty?.visibility = View.GONE
                    userSearchFragmentAdapter.setGitUser(gitRepoName!!)
                }

            }

            override fun onFailure(call: Call<GitUserModel>, t: Throwable) {
                count = 1
                getUserInfoFail(count)
                Log.d("asdasd","failCall:     "+call)
            }
        })
        return gitRepoName
    }

    //유저 검색이 실패했을 경우 1번 더
    private fun getUserInfoFail(count: Int){
        if(count==1) {
            getUserInfo(binding?.etSearch?.text.toString())
            this.count = 0
        }
    }
}