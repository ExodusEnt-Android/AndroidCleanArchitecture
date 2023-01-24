package org.techtown.presentation.feature.main

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.databinding.FragmentSavedBinding
import org.techtown.presentation.datasource.local.LocalDataSourceImpl
import org.techtown.presentation.datasource.remote.RemoteDataSourceImpl
import org.techtown.presentation.ext.navigateWithAnim
import org.techtown.presentation.feature.main.adapter.TopNewsAdapter
import org.techtown.presentation.model.Articles
import org.techtown.presentation.repository.NewsRepository
import org.techtown.presentation.repository.NewsRepositoryImpl
import org.techtown.presentation.retrofit.NewsService

class SavedFragment : BaseFragment<FragmentSavedBinding>(R.layout.fragment_saved) {

    private lateinit var savedNewsAdapter: TopNewsAdapter

    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    private var shouldRequestViewMore: Boolean = true

    private var tempSavedArticleList: ArrayList<Articles> = arrayListOf()
    var recyclerViewScrollState: Parcelable? = null

    private lateinit var database: AppDatabase

    private val newsRepository: NewsRepository by lazy {
        val localDataSource = LocalDataSourceImpl(database)
        val remoteDataSource = RemoteDataSourceImpl(NewsService.apiService)
        NewsRepositoryImpl(localDataSource, remoteDataSource)
    }

    override fun FragmentSavedBinding.onCreateView() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {// 데이터값 날라가는 경우 주의.
            recyclerViewScrollState = savedInstanceState.getParcelable("recyclerview_state")
            tempSavedArticleList = savedInstanceState.getParcelableArrayList("recyclerview_list")!!
        }
        initSet()
    }

    private fun initSet() {

        //db setting
        database = AppDatabase.getInstance(requireActivity().applicationContext)

        //로컬 디비에 있는 리스트 가지고옴.
        getSavedArticleList()

        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

        //스크롤 유지.
        binding.rvSavedNews.layoutManager?.onRestoreInstanceState(recyclerViewScrollState)
    }

    private fun getSavedArticleList() {

        viewLifecycleOwner.lifecycleScope.launch {
            newsRepository.getAllArticles().collect { savedArticles ->
                if (savedArticles.isEmpty()) {
                    shouldRequestViewMore = false
                    return@collect
                }

                savedNewsAdapter = TopNewsAdapter()
                binding.rvSavedNews.apply {
                    adapter = savedNewsAdapter
                }

                if (tempSavedArticleList.size > 0) {
                    if (tempSavedArticleList[tempSavedArticleList.lastIndex].isLoading) {
                        tempSavedArticleList.removeAt(tempSavedArticleList.lastIndex)
                        savedNewsAdapter.submitList(tempSavedArticleList.map { it.copy() })
                    }
                }

                tempSavedArticleList.clear()
                tempSavedArticleList.addAll(savedArticles)
                savedNewsAdapter.submitList(tempSavedArticleList.map { it.copy() }
                    .toMutableList())

                setListenerEvent()
            }
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
        outState.putParcelableArrayList("recyclerview_list", tempSavedArticleList)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if(enter) {
            AnimationUtils.loadAnimation(context, R.anim.stationary)
        } else {
            null
        }
    }

}