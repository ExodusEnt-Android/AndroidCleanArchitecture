package org.techtown.presentation.feature.main

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import org.techtown.data.model.DataNewsRootModel
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.local.feature.database.database.AppDatabase
import org.techtown.presentation.databinding.FragmentTopNewsBinding
import org.techtown.local.feature.news.LocalDataSourceImpl
import org.techtown.remote.feature.news.RemoteDataSourceImpl
import org.techtown.presentation.ext.navigateWithAnim
import org.techtown.presentation.feature.main.adapter.TopNewsAdapter
import org.techtown.presentation.model.Articles
import org.techtown.data.repository.news.NewsRepository
import org.techtown.data.repository.news.NewsRepositoryImpl
import org.techtown.presentation.feature.main.viewmodel.TopNewsViewModel
import org.techtown.presentation.feature.main.viewmodel.factory.ViewModelFactory
import org.techtown.presentation.model.NewsRootModel.Companion.fromData
import org.techtown.remote.retrofit.NewsService

class TopNewsFragment : BaseFragment<FragmentTopNewsBinding>(R.layout.fragment_top_news) {

    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    private lateinit var topNewsAdapter: TopNewsAdapter
    var recyclerViewScrollState: Parcelable? = null

    //db setting
    private val database: AppDatabase by lazy {
        AppDatabase.getInstance(requireActivity().applicationContext)
    }

    private val newsRepository: NewsRepository by lazy {
        val localDataSource = LocalDataSourceImpl(database)
        val remoteDataSource = RemoteDataSourceImpl(NewsService.apiService)
        NewsRepositoryImpl(localDataSource, remoteDataSource)
    }

    private val topNewsViewModel : TopNewsViewModel by viewModels {
        ViewModelFactory(newsRepository = newsRepository)
    }


    override fun FragmentTopNewsBinding.onCreateView() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            recyclerViewScrollState = savedInstanceState.getParcelable("recyclerview_state")
        }
        initSet()
        setListenerEvent()
    }

    private fun initSet() {

        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

        //스크롤 유지
        binding.rvTopNews.layoutManager?.onRestoreInstanceState(recyclerViewScrollState)

        binding.viewModel = topNewsViewModel
        binding.lifecycleOwner = this

        //어댑터 미리 세팅.
        topNewsAdapter = TopNewsAdapter()
        binding.rvTopNews.apply {
            adapter = topNewsAdapter
        }
    }

    private fun setListenerEvent() {
        //뉴스 클릭 이벤트.
        topNewsAdapter.setItemClickListener(object : TopNewsAdapter.ItemClickListener {
            override fun onItemClick(articles: Articles) {
                navController.navigateWithAnim(R.id.topNews_detail, Bundle().apply {
                    putParcelable("top_news_detail", articles)
                })
            }
        })

        binding.rvTopNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisiblePosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount?.minus(1)

                recyclerViewScrollState = binding.rvTopNews.layoutManager?.onSaveInstanceState()

                if (!recyclerView.canScrollVertically(1) && lastVisiblePosition == itemTotalCount) {
                    topNewsViewModel.getArticles()
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("recyclerview_state", recyclerViewScrollState)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if(enter) {
            AnimationUtils.loadAnimation(context, R.anim.stationary)
        } else {
            null
        }
    }
}