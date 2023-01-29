package com.example.presentation.Fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.model.Articles
import com.example.data.repository.NewsRepository
import com.example.data.repository.NewsRepositoryImpl
import com.example.presentation.*
import com.example.presentation.Adapter.NewsListAdapter
import com.example.local.Room.AppDB
import com.example.presentation.databinding.FragmentCategoryNewsBinding
import com.example.local.dataSource.LocalDataSourceImpl
import com.example.remote.dataSource.RemoteDataSourceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryNewsFragment : BaseFragment<FragmentCategoryNewsBinding>(R.layout.fragment_category_news), NewsListAdapter.OnClickListener{

    private var categoryAdapter : NewsListAdapter? = null
    private lateinit var models : ArrayList<Articles>
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    private val categoryNewsFragmentRepository : NewsRepository by lazy{
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl(
            AppDB.getInstance(requireActivity())
        )

        NewsRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        mBinding.rvCategory.layoutManager = LinearLayoutManagerWrapper(requireContext(), LinearLayoutManager.VERTICAL, false)
        categoryAdapter = context?.let { NewsListAdapter(it, this) }
        mBinding.rvCategory.adapter = categoryAdapter

        val category = arguments?.getString("category")
        if (category != null) {
            newsCategory(category)
        }
    }

    private fun newsCategory(category : String) {
        CoroutineScope(Dispatchers.IO).launch {
            categoryNewsFragmentRepository.getNews("us", category).collect{
                withContext(Dispatchers.Main){
                    val model = it.articles
                    models = ArrayList()
                    if (model.isNullOrEmpty()) return@withContext

                    for(i in model.indices){
                        models.add(model[i])
                    }
                    categoryAdapter?.setItems(models)
                }
            }
        }
    }

    override fun onItemClicked(articles: Articles, view: View) {
        when(view.id){
            R.id.cl_article -> {
                navController.navigate(R.id.newsDetailFragment, Bundle().apply {
                    putParcelable("items", articles)
                })
            }
        }
    }
}