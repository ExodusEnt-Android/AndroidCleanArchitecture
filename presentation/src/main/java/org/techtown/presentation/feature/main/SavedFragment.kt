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
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.databinding.FragmentSavedBinding
import org.techtown.presentation.ext.navigateWithAnim
import org.techtown.presentation.feature.main.adapter.TopNewsAdapter
import org.techtown.presentation.feature.main.adapter.TopNewsItemListener
import org.techtown.presentation.feature.main.viewmodel.SavedViewModel

@AndroidEntryPoint
class SavedFragment : BaseFragment<FragmentSavedBinding>(R.layout.fragment_saved) {

    private lateinit var savedNewsAdapter: TopNewsAdapter

    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    var recyclerViewScrollState: Parcelable? = null

    private val savedViewModel: SavedViewModel by viewModels()

    override fun FragmentSavedBinding.onCreateView() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {// 데이터값 날라가는 경우 주의.
            recyclerViewScrollState = savedInstanceState.getParcelable("recyclerview_state")
        }
        initSet()
    }

    private fun initSet() {

        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

        //스크롤 유지.
        binding.rvSavedNews.layoutManager?.onRestoreInstanceState(recyclerViewScrollState)

        binding.viewModel = savedViewModel
        binding.lifecycleOwner = this

        savedNewsAdapter = TopNewsAdapter(TopNewsItemListener { articles ->
            navController.navigateWithAnim(R.id.topNews_detail, Bundle().apply {
                putParcelable("top_news_detail", articles)
            })
        })
        binding.rvSavedNews.apply {
            adapter = savedNewsAdapter
        }

        savedViewModel.fetchSavedArticleList()
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