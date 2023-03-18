package org.techtown.presentation.feature.main

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.databinding.FragmentTopNewsBinding
import org.techtown.presentation.ext.navigateWithAnim
import org.techtown.presentation.feature.main.adapter.TopNewsAdapter
import org.techtown.presentation.feature.main.adapter.TopNewsItemListener
import org.techtown.presentation.feature.main.viewmodel.TopNewsViewModel

@AndroidEntryPoint
class TopNewsFragment : BaseFragment<FragmentTopNewsBinding>(R.layout.fragment_top_news) {

    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    private lateinit var topNewsAdapter: TopNewsAdapter
    var recyclerViewScrollState: Parcelable? = null

    private val topNewsViewModel: TopNewsViewModel by viewModels()


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
        topNewsAdapter = TopNewsAdapter(TopNewsItemListener { articles ->
            navController.navigateWithAnim(R.id.topNews_detail, Bundle().apply {
                putParcelable("top_news_detail", articles)
            })
        })
        binding.rvTopNews.apply {
            adapter = topNewsAdapter
        }
    }

    private fun setListenerEvent() {
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