package com.example.presentation.Fragment

import ViewModelFactory
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.model.Articles
import com.example.data.repository.NewsRepository
import com.example.data.repository.NewsRepositoryImpl
import com.example.presentation.*
import com.example.presentation.Adapter.NewsListAdapter
import com.example.local.Room.AppDB
import com.example.presentation.databinding.FragmentSavedBinding
import com.example.local.dataSource.LocalDataSourceImpl
import com.example.presentation.ViewModel.SavedViewModel
import com.example.presentation.model.PresentationArticles
import com.example.remote.dataSource.RemoteDataSourceImpl
import timber.log.Timber

class SavedFragment : BaseFragment<FragmentSavedBinding>(R.layout.fragment_saved) , NewsListAdapter.OnClickListener{

    private var saveNewsAdapter : NewsListAdapter? = null
    private lateinit var models : List<Articles>
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    private val savedFragmentRepository : NewsRepository by lazy {
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
        saveNewsAdapter = context?.let { NewsListAdapter(it, this) }
        mBinding.rvTopNews.adapter = saveNewsAdapter

        savedViewModel.newsSource()
        Timber.d("asdasdasdasd onViewCreated")
        getDataFromVM()
    }

    private val savedViewModel: SavedViewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = ViewModelFactory(repository = savedFragmentRepository)
        )[SavedViewModel::class.java]
    }

    //저장된 뉴스 id를 통해 보여주기
    private fun getDataFromVM(){
        savedViewModel.articleList.observe(viewLifecycleOwner){
            Timber.d("asdasdasd -> "+it.size)
            saveNewsAdapter?.setItems(it)
        }
    }

    override fun onItemClicked(item: PresentationArticles, view: View) {
        when(view.id){
            R.id.cl_article -> {
                navController.navigate(R.id.newsDetailFragment,  Bundle().apply {
                    putParcelable("items", item)
                })
            }
        }
    }
}