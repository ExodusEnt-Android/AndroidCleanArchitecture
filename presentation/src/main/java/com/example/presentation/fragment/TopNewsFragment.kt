package com.example.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.activity.LoginActivity
import com.example.presentation.activity.SplashActivity
import com.example.presentation.adapter.TopNewsListAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.const.Const
import com.example.presentation.databinding.FragmentTopNewsBinding
import com.example.presentation.model.Article
import com.example.presentation.model.BaseDataModel
import com.example.presentation.retrofit.RetrofitHelper
import com.example.presentation.room.LocalDataBase
import com.example.presentation.util.PreferenceManager
import com.example.presentation.util.Util.navigateWithAnim
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class TopNewsFragment : BaseFragment<FragmentTopNewsBinding>(R.layout.fragment_top_news) {

    lateinit var topNewsListAdapter: TopNewsListAdapter
    private var totalResult = DEFAULT_LIST_SIZE
    private var page = 1
    private var rcyScrollLState: Parcelable? = null
    private var isAlreadyInitialized = false
    private val topNewsList = mutableListOf<Article>()

    //네비게이션 컨트롤러
    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    override fun FragmentTopNewsBinding.onCreateView() {
        initSet()
        setListenerEvent()
        setToolbar()
    }

    private fun initSet() {

        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

        topNewsListAdapter = TopNewsListAdapter()
        binding.rvTopNewsList.apply {
            adapter = topNewsListAdapter
        }

        if (!isAlreadyInitialized) {
            isAlreadyInitialized = true
            //탑 뉴스기사 리스트 가져오기
            getTopNewsList()
        } else {
            topNewsListAdapter.submitList(topNewsList)
            binding.rvTopNewsList.layoutManager?.onRestoreInstanceState(rcyScrollLState)
        }
    }

    //리스너 이벤트 모음
    private fun setListenerEvent() {
        topNewsListAdapter.setOnTopNewsItemClickListener(object :
            TopNewsListAdapter.ItemClickListener {
            override fun onTopNewItemClick(article: Article) {
                navController.navigateWithAnim(R.id.articleDetailFragment, Bundle().apply {
                    putParcelable(Const.PARAM_ARTICLE_MODEL,article)//닉네임 보냄
                })
            }
        })

        binding.rvTopNewsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 마지막에 보이는 포지션
                val lastVisiblePosition =
                    (binding.rvTopNewsList.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                val lastPosition = topNewsListAdapter.itemCount - 1

                //리사이클러뷰 스크롤 위치 뷰모델에 캐싱 -> 유지하기 위해서
                rcyScrollLState =
                    binding.rvTopNewsList.layoutManager?.onSaveInstanceState()

                // 스크롤이 끝에 도달했는지 확인후  api를 요청해서 다음 페이지를 받아온다.
                if (!recyclerView.canScrollVertically(1)
                    && lastVisiblePosition == lastPosition
                ) {
                    getTopNewsList()
                }
            }
        })

        //toolbar title top news 누르면 로그아웃 처리해줌.
        binding.toolbar.tvTitle.setOnClickListener {
            logout()
        }
    }

    //로그아웃 처리
    private fun logout(){
        LocalDataBase.destroyInstance()
        PreferenceManager.removeAllPreference(requireActivity())//로그인 체크 값 다 지워줌.
        startActivity(Intent(requireActivity(), SplashActivity::class.java))
        requireActivity().finish()
    }


    //toolbar setting
    private fun setToolbar() {
        binding.toolbar.tvTitle.text = requireActivity().getString(R.string.top_news)
    }

    //탑 뉴스 리스트 가져오기
    private fun getTopNewsList() {

        //전체 reesult 값이  현재 뉴스 리스트 값과 같거나 작으면, 페이징  처리 마감. 해줌.
        if (totalResult != DEFAULT_LIST_SIZE && totalResult <= topNewsListAdapter.currentList.size) {
            return
        }

        RetrofitHelper.apiService.getTopHeadLines(page = page, pageSize = Const.PageSize)
            .enqueue(object : Callback<BaseDataModel<Article>> {
                override fun onResponse(
                    call: Call<BaseDataModel<Article>>,
                    response: Response<BaseDataModel<Article>>
                ) {

                    val result = response.body()

                    //새 뉴스 게시글리스트
                    totalResult = result?.totalResults ?: 0
                    val newTopNewsArticleList = result?.articles

                    if (newTopNewsArticleList != null) {
                        topNewsList.addAll(newTopNewsArticleList)
                    }

                    page++
                    topNewsListAdapter.submitList(topNewsList)

                    //기존  스크롤  위치 정보 캐싱되어있으면 다시 적용 해줌.
                    if (rcyScrollLState != null) {
                        binding.rvTopNewsList.layoutManager?.onRestoreInstanceState(rcyScrollLState)
                    }
                }

                override fun onFailure(call: Call<BaseDataModel<Article>>, t: Throwable) {
                    showToast(t.message.toString())
                }
            })
    }

    companion object {
        const val DEFAULT_LIST_SIZE = -1
    }
}