package com.example.presentation.fragment

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.adapter.TopNewsListAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.const.Const
import com.example.presentation.databinding.FragmentSavedBinding
import com.example.presentation.model.Article
import com.example.presentation.repository.TopNewsRepository
import com.example.presentation.repository.TopNewsRepositoryImpl
import com.example.presentation.retrofit.RetrofitHelper
import com.example.presentation.room.LocalDataBase
import com.example.presentation.source.local.SavedNewsLocalDataSourceImpl
import com.example.presentation.source.remote.TopNewsRemoteDataSourceImpl
import com.example.presentation.util.Util.getSavedNewsArticleList
import com.example.presentation.util.Util.navigateWithAnim
import timber.log.Timber

class SavedFragment:BaseFragment<FragmentSavedBinding>(R.layout.fragment_saved) {

    //네비게이션 컨트롤러
    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    private var rcyScrollLState: Parcelable? = null
    lateinit var topNewsListAdapter: TopNewsListAdapter

    //respository 가져옴
    private val topNewsRepository: TopNewsRepository by lazy{
        val topNewsRemoteDataSource = TopNewsRemoteDataSourceImpl(RetrofitHelper)
        val savedNewsLocalDataSource = SavedNewsLocalDataSourceImpl(LocalDataBase.getInstance(requireActivity()),requireActivity())
        TopNewsRepositoryImpl(topNewsRemoteDataSource,savedNewsLocalDataSource)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //스크롤 state 다시 넣어줌.
        rcyScrollLState = savedInstanceState?.getParcelable(PARAM_RCY_SCROLL_STATE)
    }

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        //햔제 스크롤 포지션 저장
        outState.putParcelable(PARAM_RCY_SCROLL_STATE,rcyScrollLState)
    }

    private fun getSavedNewsList(){
        //저장 여부 체크
        topNewsRepository.getSavedArticleList { articles, error ->
            if(articles != null){
                topNewsListAdapter.submitList(articles)
                binding.rvSavedNewsList.layoutManager?.onRestoreInstanceState(rcyScrollLState)
            }else{
                showToast(error?.message.toString())
            }
        }
    }


    //toolbar setting
    private fun setToolbar(){
        binding.toolbar.tvTitle.text = getString(R.string.saved)
    }

    companion object{
        const val PARAM_RCY_SCROLL_STATE = "param_rcy_scroll_state"
    }
}