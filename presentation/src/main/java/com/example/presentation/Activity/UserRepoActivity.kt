package com.example.presentation.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.presentation.Adapter.UserRepoAdapter
import com.example.presentation.Fragment.GitRetrofit
import com.example.presentation.Model.GitRepoModel
import com.example.presentation.Model.UserItemModel
import com.example.presentation.databinding.FragmentUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepoActivity :AppCompatActivity(){

    private lateinit var binding: FragmentUserBinding
    private lateinit var userRepoAdapter : UserRepoAdapter
    private lateinit var mGlideRequestManager: RequestManager
    private var userItemModel : UserItemModel? = null

    private var nowName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //view binding
        binding = FragmentUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mGlideRequestManager = Glide.with(this)
        userRepoAdapter = UserRepoAdapter(mGlideRequestManager)
        binding.rcvUser.apply {
            adapter = userRepoAdapter
        }
        userItemModel = intent.extras?.getParcelable<UserItemModel>(PARAM_USER) as UserItemModel

        clickEvent()

        getUserRepoInfo(userItemModel!!.login)
        binding.btnSearch.setOnClickListener {  //검색버튼 누르면 유저 정보 부르는 api 호출
            getUserRepoInfo(binding.etSearch.text.toString())
        }
    }

    private fun getUserRepoInfo(userName : String): ArrayList<GitRepoModel>? {
        var gitRepoName : ArrayList<GitRepoModel>? = ArrayList<GitRepoModel>()


        GitRetrofit.repoService.getUserRepositories(userName).enqueue(object :
            Callback<ArrayList<GitRepoModel>> {
            override fun onResponse(call: Call<ArrayList<GitRepoModel>>, response: Response<ArrayList<GitRepoModel>>) {
                Log.d("mingue","arrayList 값 :: "+response.body())
                gitRepoName = response.body()

                if(gitRepoName.isNullOrEmpty()){    //검색된 유저가 없을 경우
                    binding.tvEmpty.visibility = View.VISIBLE
                }
                else{                               //검색된 유저가 있을 경우
                    binding.tvEmpty.visibility = View.GONE
                    userRepoAdapter.setGitUserArray(gitRepoName!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<GitRepoModel>>, t: Throwable?) {
                getUserInfoFail(userName)
            }
        })
        return gitRepoName
    }

    //즐겨찾기 할 때 쓸 계획
    private fun clickEvent() {
        userRepoAdapter.setOnItemClickListener(object : UserRepoAdapter.OnItemClickListener{
            override fun clickUserLike(gitRepoModel: GitRepoModel) {
                Log.d("click","UserModel::"+gitRepoModel)
            }

        })

    }

    //정보 가져오는 것 한번 실패했을 경우 1번 더 호출
    private fun getUserInfoFail(userName : String){
        if(nowName != userName){
            nowName = userName
            getUserRepoInfo(binding.etSearch.text.toString())
        }
    }

    companion object{
        const val PARAM_USER = "param_user"

        //스플래시에서 준 데이터 받음.
        fun createIntent(context: Context, userItemModel: UserItemModel): Intent {
            val intent = Intent(context, UserRepoActivity::class.java)
            val args = Bundle()
            args.putParcelable(PARAM_USER, userItemModel)
            intent.putExtras(args)

            return intent
        }
    }
}