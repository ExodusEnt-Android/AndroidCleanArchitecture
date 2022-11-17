package org.techtown.presentation

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.presentation.adapter.TopNewsAdapter
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.databinding.FragmentFirstBinding
import org.techtown.presentation.datasource.local.NewsLocalDatasource
import org.techtown.presentation.datasource.local.NewsLocalDatasourceImpl
import org.techtown.presentation.datasource.remote.NewsRemoteDatasourceImpl
import org.techtown.presentation.ext.navigateWithAnim
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.NewsRootModel
import org.techtown.presentation.repository.NewsRepository
import org.techtown.presentation.repository.NewsRepositoryImpl
import org.techtown.presentation.retrofit.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment : BaseFragment<FragmentFirstBinding>(R.layout.fragment_first) {


    private lateinit var topNewsAdapter: TopNewsAdapter

    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    private var offset = 1
    private var limit = 5

    private var shouldRequestViewMore: Boolean = true

    private var tempArticleList: ArrayList<Articles> = arrayListOf()
    var recyclerViewScrollState: Parcelable? = null

    private val database: AppDatabase by lazy {
        AppDatabase.getInstance(requireActivity().applicationContext)
    }

    private val newsRepository : NewsRepository by lazy {
        val newsRemoteDatasource = NewsRemoteDatasourceImpl(NewsService)
        val newsLocalDatasource = NewsLocalDatasourceImpl(database)
        NewsRepositoryImpl(newsRemoteDatasource, newsLocalDatasource)
    }

    override fun FragmentFirstBinding.onCreateView() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            recyclerViewScrollState = savedInstanceState.getParcelable("recyclerview_state")
            tempArticleList = savedInstanceState.getParcelableArrayList("recyclerview_list")!!
        }
        initSet()
    }

    private fun initSet() {
        if (tempArticleList.isEmpty() && !this::topNewsAdapter.isInitialized) {
            getArticles()
        } else {
            binding.rvTopNews.apply {
                adapter = topNewsAdapter
            }
            topNewsAdapter.submitList(tempArticleList)
        }


        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

        //스크롤 유지
        binding.rvTopNews.layoutManager?.onRestoreInstanceState(recyclerViewScrollState)
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

                if (!recyclerView.canScrollVertically(1)
                    && lastVisiblePosition == itemTotalCount
                    && shouldRequestViewMore
                ) {
                    getArticles()
                }

                if (!shouldRequestViewMore) {
                    topNewsAdapter.deleteLoading()
                }

            }
        })
    }

    private fun getArticles() {
        if (tempArticleList.isNotEmpty()) {
            if (!tempArticleList[tempArticleList.lastIndex].isLoading) {
                tempArticleList.add(Articles(isLoading = true, title = "", url = ""))
                topNewsAdapter.submitList(tempArticleList.map { it.copy() }.toMutableList())
            }
        }

        newsRepository.getTopHeadlinesArticles(
            country = "us",
            pageSize = limit,
            offset = offset,
            category = null
        )
            .enqueue(object : Callback<NewsRootModel> {
                override fun onResponse(
                    call: Call<NewsRootModel>,
                    response: Response<NewsRootModel>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            if (!this@FirstFragment::topNewsAdapter.isInitialized) {
                                topNewsAdapter = TopNewsAdapter()
                                binding.rvTopNews.apply {
                                    adapter = topNewsAdapter
                                }
                            }

                            if (tempArticleList.size > 0) {
                                if (tempArticleList[tempArticleList.lastIndex].isLoading) {
                                    tempArticleList.removeAt(tempArticleList.lastIndex)
                                    topNewsAdapter.submitList(tempArticleList.map { it.copy() })
                                }
                            }


                            tempArticleList.addAll(data.articles)
                            topNewsAdapter.submitList(tempArticleList.map { it.copy() }
                                .toMutableList())

                            setListenerEvent()

                            offset += 1
                        } else {
                            shouldRequestViewMore = false
                        }
                    }
                }

                override fun onFailure(call: Call<NewsRootModel>, t: Throwable) {
                    Toast.makeText(requireActivity(), t.message, Toast.LENGTH_LONG).show()
                }

            })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("VALUE_CHECK", "onSaveInstanceState")
        outState.putParcelable("recyclerview_state", recyclerViewScrollState)
        outState.putParcelableArrayList("recyclerview_list", tempArticleList)
    }


}