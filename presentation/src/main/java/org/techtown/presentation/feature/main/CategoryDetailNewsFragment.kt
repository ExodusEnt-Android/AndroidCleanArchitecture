package org.techtown.presentation.feature.main

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.databinding.FragmentCategoryDetailNewsBinding
import org.techtown.presentation.ext.navigateWithAnim
import org.techtown.presentation.feature.main.adapter.TopNewsAdapter
import org.techtown.presentation.feature.main.adapter.TopNewsItemListener
import org.techtown.presentation.feature.main.viewmodel.CategoryDetailNewsViewModel

@AndroidEntryPoint
class CategoryDetailNewsFragment :
    BaseFragment<FragmentCategoryDetailNewsBinding>(R.layout.fragment_category_detail_news) {

    private lateinit var categoryNewsAdapter: TopNewsAdapter

    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    var recyclerViewScrollState: Parcelable? = null

    private val categoryDetailNewsViewModel: CategoryDetailNewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun FragmentCategoryDetailNewsBinding.onCreateView() {
        initSet()
        setListenerEvent()
    }

    private fun initSet() {

        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

        binding.rvCategoryDetail.layoutManager?.onRestoreInstanceState(recyclerViewScrollState)

        binding.viewModel = categoryDetailNewsViewModel
        binding.lifecycleOwner = this

        categoryNewsAdapter = TopNewsAdapter(TopNewsItemListener { articles ->
            navController.navigateWithAnim(R.id.topNews_detail, Bundle().apply {
                putParcelable("top_news_detail", articles)
            })
        })
        binding.rvCategoryDetail.apply {
            adapter = categoryNewsAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            recyclerViewScrollState = savedInstanceState.getParcelable("recyclerview_state")
        }
    }

    private fun setListenerEvent() {

        binding.rvCategoryDetail.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisiblePosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount?.minus(1)

                recyclerViewScrollState =
                    binding.rvCategoryDetail.layoutManager?.onSaveInstanceState()

                if (!recyclerView.canScrollVertically(1)
                    && lastVisiblePosition == itemTotalCount
                ) {
                    categoryDetailNewsViewModel.getCategoryArticles()
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("recyclerview_state", recyclerViewScrollState)
    }
}