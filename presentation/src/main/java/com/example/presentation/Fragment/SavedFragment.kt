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
import com.example.presentation.databinding.FragmentSavedBinding
import com.example.local.dataSource.LocalDataSourceImpl
import com.example.presentation.viewModel.SavedViewModel
import com.example.presentation.model.PresentationArticles
import com.example.remote.dataSource.RemoteDataSourceImpl
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint  //객체를 주입할 대상에게 선언 (Activity ,Fragment ,View ,Service ,BroadcastReceiver)
class SavedFragment : BaseFragment<FragmentSavedBinding>(R.layout.fragment_saved) , NewsListAdapter.OnClickListener{

    private var saveNewsAdapter : NewsListAdapter? = null
    private lateinit var models : List<Articles>
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    private val savedViewModel: SavedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvTopNews.layoutManager = LinearLayoutManagerWrapper(requireContext(), LinearLayoutManager.VERTICAL, false)
        mBinding.viewModel = savedViewModel
        mBinding.lifecycleOwner = this

        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        saveNewsAdapter = context?.let { NewsListAdapter(it, this) }
        mBinding.rvTopNews.adapter = saveNewsAdapter

        savedViewModel.newsSource()
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