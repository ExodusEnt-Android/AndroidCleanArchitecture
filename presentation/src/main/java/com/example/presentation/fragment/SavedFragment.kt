package com.example.presentation.fragment

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.adapter.TopNewsListAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.const.Const
import com.example.presentation.databinding.FragmentSavedBinding
import com.example.presentation.model.Article
import com.example.presentation.util.Util.getSavedNewsArticleList
import com.example.presentation.util.Util.navigateWithAnim

class SavedFragment:BaseFragment<FragmentSavedBinding>(R.layout.fragment_saved) {

    //네비게이션 컨트롤러
    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    private var rcyScrollLState: Parcelable? = null
    lateinit var topNewsListAdapter: TopNewsListAdapter
    private val topNewsList = mutableListOf<Article>()
    private var isAlreadyInitialized = false


    override fun FragmentSavedBinding.onCreateView() {
        initSet()
        setListenerEvent()
    }
    private fun initSet(){

        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

        topNewsListAdapter = TopNewsListAdapter()
        binding.rvSavedNewsList.apply {
            adapter = topNewsListAdapter
        }

        setToolbar()
        getSavedNewsList()
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

        binding.rvSavedNewsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                //리사이클러뷰 스크롤 위치 뷰모델에 캐싱 -> 유지하기 위해서
                rcyScrollLState =
                    binding.rvSavedNewsList.layoutManager?.onSaveInstanceState()
            }
        })
    }

    private fun getSavedNewsList(){

        //저장 여부 체크
        requireActivity().getSavedNewsArticleList { list,error->
            if(list != null){
                topNewsList.clear()
                topNewsList.addAll(list)
                topNewsListAdapter.currentList.clear()
                topNewsListAdapter.submitList(topNewsList)

                //기존  스크롤  위치 정보 캐싱되어있으면 다시 적용 해줌.
                if (rcyScrollLState != null) {
                    binding.rvSavedNewsList.layoutManager?.onRestoreInstanceState(rcyScrollLState)
                }

            }else{
                showToast(error?.message.toString())
            }
        }

    }


    //toolbar setting
    private fun setToolbar(){
        binding.toolbar.tvTitle.text = getString(R.string.saved)
    }
}