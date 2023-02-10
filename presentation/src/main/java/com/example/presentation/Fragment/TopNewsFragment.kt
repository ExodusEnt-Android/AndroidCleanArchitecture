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
import com.example.presentation.databinding.FragmentTopNewsBinding
import com.example.local.dataSource.LocalDataSourceImpl
import com.example.presentation.model.PresentationArticles
import com.example.presentation.model.PresentationArticles.Companion.fromData
import com.example.remote.dataSource.RemoteDataSourceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopNewsFragment : BaseFragment<FragmentTopNewsBinding>(R.layout.fragment_top_news), NewsListAdapter.OnClickListener{

    private var topNewsAdapter : NewsListAdapter? = null
    private lateinit var models : ArrayList<Articles>
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    private val topNewsFragmentRepository : NewsRepository by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl(context?.let {
            AppDB.getInstance(it)
        }!!)

        NewsRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        mBinding.rvTopNews.layoutManager = LinearLayoutManagerWrapper(requireContext(), LinearLayoutManager.VERTICAL, false)
        topNewsAdapter = context?.let { NewsListAdapter(it, this) }
        mBinding.rvTopNews.adapter = topNewsAdapter
        topNews()
    }

    private fun topNews() {

        CoroutineScope(Dispatchers.IO).launch {
            topNewsFragmentRepository.getNews("us", null).collect {

                withContext(Dispatchers.Main) {
                    val model = it.articles

                    models = ArrayList()
                    if (model.isNullOrEmpty()) return@withContext

                    for (i in model.indices) {
                        models.add(model[i])
                    }

                    val items = models.map { it.fromData() }

                    topNewsAdapter?.setItems(items)
                }
            }
        }
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