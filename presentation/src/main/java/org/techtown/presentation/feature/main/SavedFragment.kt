package org.techtown.presentation.feature.main

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.databinding.FragmentSavedBinding
import org.techtown.local.feature.news.LocalDataSourceImpl
import org.techtown.remote.feature.news.RemoteDataSourceImpl
import org.techtown.presentation.ext.navigateWithAnim
import org.techtown.presentation.feature.main.adapter.TopNewsAdapter
import org.techtown.presentation.model.Articles
import org.techtown.data.repository.news.NewsRepository
import org.techtown.data.repository.news.NewsRepositoryImpl
import org.techtown.local.feature.database.database.AppDatabase
import org.techtown.presentation.feature.main.viewmodel.SavedViewModel
import org.techtown.presentation.feature.main.viewmodel.factory.ViewModelFactory
import org.techtown.presentation.model.Articles.Companion.fromData
import org.techtown.remote.retrofit.NewsService

class SavedFragment : BaseFragment<FragmentSavedBinding>(R.layout.fragment_saved) {

    private lateinit var savedNewsAdapter: TopNewsAdapter

    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    var recyclerViewScrollState: Parcelable? = null

    private val database: AppDatabase by lazy {
        AppDatabase.getInstance(requireActivity().applicationContext)
    }

    private val newsRepository: NewsRepository by lazy {
        val localDataSource = LocalDataSourceImpl(database)
        val remoteDataSource = RemoteDataSourceImpl(NewsService.apiService)
        NewsRepositoryImpl(localDataSource, remoteDataSource)
    }

    private val savedViewModel: SavedViewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = ViewModelFactory(newsRepository = newsRepository)
        )[SavedViewModel::class.java]
    }

    override fun FragmentSavedBinding.onCreateView() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {// 데이터값 날라가는 경우 주의.
            recyclerViewScrollState = savedInstanceState.getParcelable("recyclerview_state")
        }
        initSet()
        getDataFromVM()
        setListenerEvent()
    }

    private fun getDataFromVM() {
        savedViewModel.savedArticleList.observe(viewLifecycleOwner) { savedArticleList ->
            savedNewsAdapter.submitList(savedArticleList.map { it.copy() }.toMutableList())
        }
    }

    private fun initSet() {

        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

        //스크롤 유지.
        binding.rvSavedNews.layoutManager?.onRestoreInstanceState(recyclerViewScrollState)

        savedNewsAdapter = TopNewsAdapter()
        binding.rvSavedNews.apply {
            adapter = savedNewsAdapter
        }
    }

    private fun setListenerEvent() {
        savedNewsAdapter.setItemClickListener(object : TopNewsAdapter.ItemClickListener {
            override fun onItemClick(articles: Articles) {
                navController.navigateWithAnim(R.id.topNews_detail, Bundle().apply {
                    putParcelable("top_news_detail", articles)
                })
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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