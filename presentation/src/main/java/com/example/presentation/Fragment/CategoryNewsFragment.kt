package com.example.presentation.fragment

import ViewModelFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.model.Articles
import com.example.data.repository.NewsRepository
import com.example.data.repository.NewsRepositoryImpl
import com.example.presentation.*
import com.example.presentation.adapter.NewsListAdapter
import com.example.local.Room.AppDB
import com.example.presentation.databinding.FragmentCategoryNewsBinding
import com.example.local.dataSource.LocalDataSourceImpl
import com.example.presentation.viewModel.CategoryNewsViewModel
import com.example.presentation.model.PresentationArticles
import com.example.remote.dataSource.RemoteDataSourceImpl

class CategoryNewsFragment : BaseFragment<FragmentCategoryNewsBinding>(R.layout.fragment_category_news), NewsListAdapter.OnClickListener{

    private var categoryAdapter : NewsListAdapter? = null
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    private val categoryNewsFragmentRepository : NewsRepository by lazy{
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl(
            AppDB.getInstance(requireActivity())
        )

        NewsRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
    }

    private val categoryNewsViewModel: CategoryNewsViewModel by viewModels {
        ViewModelFactory(repository = categoryNewsFragmentRepository)
    }

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