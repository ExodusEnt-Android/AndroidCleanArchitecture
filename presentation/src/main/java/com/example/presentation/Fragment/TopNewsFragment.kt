package com.example.presentation.fragment

import ViewModelFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.model.Articles
import com.example.presentation.*
import com.example.presentation.adapter.NewsListAdapter
import com.example.presentation.databinding.FragmentTopNewsBinding
import com.example.presentation.viewModel.TopNewsViewModel
import com.example.presentation.model.PresentationArticles
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint  //객체를 주입할 대상에게 선언 (Activity ,Fragment ,View ,Service ,BroadcastReceiver)
class TopNewsFragment : BaseFragment<FragmentTopNewsBinding>(R.layout.fragment_top_news), NewsListAdapter.OnClickListener{

    private var topNewsAdapter : NewsListAdapter? = null
    private lateinit var models : ArrayList<Articles>
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController


    private val topNewsViewModel: TopNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvTopNews.layoutManager = LinearLayoutManagerWrapper(requireContext(), LinearLayoutManager.VERTICAL, false)
        mBinding.viewModel = topNewsViewModel
        mBinding.lifecycleOwner = this

        topNewsAdapter = context?.let { NewsListAdapter(it, this) }
        mBinding.rvTopNews.adapter = topNewsAdapter

        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

    }

    override fun onItemClicked(item: PresentationArticles, view: View) {
        when(view.id){
            R.id.cl_article -> {
                navController.navigate(R.id.newsDetailFragment, Bundle().apply {
                    putParcelable("items", item)
                })
            }
        }
    }

}