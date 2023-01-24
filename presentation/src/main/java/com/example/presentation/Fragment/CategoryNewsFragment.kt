package com.example.presentation.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.*
import com.example.presentation.Adapter.NewsListAdapter
import com.example.presentation.Room.AppDB
import com.example.presentation.databinding.FragmentCategoryNewsBinding
import com.example.presentation.datasource.local.LocalDataSourceImpl
import com.example.presentation.datasource.remote.RemoteDataSourceImpl
import com.example.presentation.repository.NewsRepository
import com.example.presentation.repository.NewsRepositoryImpl
import com.example.presentation.retrofit.ApiService
import com.example.presentation.retrofit.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class CategoryNewsFragment : BaseFragment<FragmentCategoryNewsBinding>(R.layout.fragment_category_news), NewsListAdapter.OnClickListener{

    private var categoryAdapter : NewsListAdapter? = null
    private lateinit var models : ArrayList<Articles>
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    private val categoryNewsFragmentRepository : NewsRepository by lazy{
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl(AppDB.getInstance(requireActivity()))

        NewsRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = FragmentCategoryNewsBinding.inflate(inflater, container, false)
        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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