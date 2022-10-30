package com.example.presentation.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.adapter.TopNewsListAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.const.Const
import com.example.presentation.databinding.FragmentTopNewsBinding
import com.example.presentation.model.Article
import com.example.presentation.model.BaseDataModel
import com.example.presentation.repository.TopNewsRepository
import com.example.presentation.repository.TopNewsRepositoryImpl
import com.example.presentation.retrofit.RetrofitHelper
import com.example.presentation.room.LocalDataBase
import com.example.presentation.source.local.SavedNewsLocalDataSourceImpl
import com.example.presentation.source.remote.TopNewsRemoteDataSourceImpl
import com.example.presentation.util.Util.navigateWithAnim
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class CategoryTopNewsFragment:BaseFragment<FragmentTopNewsBinding>(R.layout.fragment_top_news) {

    //네비게이션 컨트롤러
    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment
    private var totalResult = TopNewsFragment.DEFAULT_LIST_SIZE
    private var page = 1
    private var rcyScrollLState: Parcelable? = null
    lateinit var topNewsListAdapter: TopNewsListAdapter
    private val topNewsList = mutableListOf<Article>()
    private var isAlreadyInitialized = false
    private var categoryString  = ""

    //reposotory 구성 해줌.
    private val topNewsRepository: TopNewsRepository by lazy{
        val topNewsRemoteDataSource = TopNewsRemoteDataSourceImpl(RetrofitHelper)
        val savedNewsLocalDataSource = SavedNewsLocalDataSourceImpl(LocalDataBase.getInstance(requireActivity()),requireActivity())
        TopNewsRepositoryImpl(topNewsRemoteDataSource,savedNewsLocalDataSource)
    }

    override fun FragmentTopNewsBinding.onCreateView() {
        initSet()
        setListenerEvent()
    }


    //화면실행시 맨처음에는 navigation 실행시 option으로 줬던  enter 애니메이션을 시작하고,
    //그외에는 stationay를 주어 enteranimation을 없애준다.-> 계속 메인 탭 이동시  이미 navigate된 fragment가 기존 설정한
    //enter animation을 실행하여서  이렇게 예외처리 해줌.
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if ((enter && arguments?.getBoolean(Const.PARAM_SCREEN_INITIALIZED,false) == true)) {
            AnimationUtils.loadAnimation(context, R.anim.stationary)
        } else {
            arguments?.putBoolean(Const.PARAM_SCREEN_INITIALIZED,true)
            null
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

        //뒤로가기 버튼 클릭시
        binding.toolbarBack.ivBackArrow.setOnClickListener {
            navController.popBackStack()
        }
    }

    //툴바 세팅
    private fun setToolbar(){
        binding.toolbar.root.visibility = View.INVISIBLE
        binding.toolbarBack.root.visibility = View.VISIBLE
        binding.toolbarBack.tvTitle.text = "Category - $categoryString"
    }

    private fun initSet(){

        categoryString = arguments?.getString(Const.PARAM_ARTICLE_CATEGORY)?:""

        setToolbar()

        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

        topNewsListAdapter = TopNewsListAdapter()
        binding.rvTopNewsList.apply {
            adapter = topNewsListAdapter
        }


        //맨처음 initalize 안되었을때만 체크해서 혹시 arguments에 저장된거 있는지 체크
        //탭이동시에는 이렇게 해줘야됨.
        //navigation 이동은 이미 스택에 있어서, argument에 있는걸 가져오면 안됨.
        if(!isAlreadyInitialized){
            isAlreadyInitialized = true
            arguments?.apply {
                getParcelableArrayList<Article>(ARTICLE_LIST)?.toMutableList()
                    ?.let {
                        topNewsList.addAll(it)
                    }
                getInt(TOTAL_RESULT,TopNewsFragment.DEFAULT_LIST_SIZE).let {
                    totalResult = it
                }
                getInt(PAGE,1).let {
                    page = it
                }
            }
        }


        if(topNewsList.isNotEmpty()){//만약에 캐싱된 뉴스 리스트가 있으면  바로 리스트 부터 뿌려줌.
            topNewsListAdapter.submitList(topNewsList)
        }else{
            //탑 뉴스기사 리스트 가져오기
            getTopNewsList()
        }
    }


    //탑 뉴스 리스트 가져오기
    private fun getTopNewsList() {

        //전체 reesult 값이  현재 뉴스 리스트 값과 같거나 작으면, 페이징  처리 마감. 해줌.
        if (totalResult != TopNewsFragment.DEFAULT_LIST_SIZE && totalResult <= topNewsListAdapter.currentList.size) {
            return
        }

        topNewsRepository.getTopHeadLines(page = page, pageSize = Const.PageSize, category = categoryString)
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

                    arguments?.putParcelableArrayList(ARTICLE_LIST,topNewsList as ArrayList)
                    arguments?.putInt(PAGE,page)
                    arguments?.putInt(TOTAL_RESULT,totalResult)

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
        const val TOTAL_RESULT = "totalResult"
        const val PAGE = "page"
        const val ARTICLE_LIST = "article_list"
    }
}