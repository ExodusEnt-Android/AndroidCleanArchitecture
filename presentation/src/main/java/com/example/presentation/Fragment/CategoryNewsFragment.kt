package com.example.presentation.fragment

import ViewModelFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.*
import com.example.presentation.adapter.NewsListAdapter
import com.example.presentation.databinding.FragmentCategoryNewsBinding
import com.example.presentation.viewModel.CategoryNewsViewModel
import com.example.presentation.model.PresentationArticles
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint  //객체를 주입할 대상에게 선언 (Activity ,Fragment ,View ,Service ,BroadcastReceiver)
class CategoryNewsFragment : BaseFragment<FragmentCategoryNewsBinding>(R.layout.fragment_category_news), NewsListAdapter.OnClickListener{

    private var categoryAdapter : NewsListAdapter? = null
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController


    private val categoryNewsViewModel: CategoryNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvCategory.layoutManager = LinearLayoutManagerWrapper(requireContext(), LinearLayoutManager.VERTICAL, false)
        mBinding.viewModel = categoryNewsViewModel
        mBinding.lifecycleOwner = this

        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        categoryAdapter = context?.let { NewsListAdapter(it, this) }
        mBinding.rvCategory.adapter = categoryAdapter
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