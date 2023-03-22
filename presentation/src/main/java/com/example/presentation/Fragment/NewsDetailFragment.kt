package com.example.presentation.fragment

import ViewModelFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.data.repository.NewsRepository
import com.example.data.repository.NewsRepositoryImpl
import com.example.presentation.R
import com.example.local.Room.AppDB
import com.example.presentation.databinding.FragmentNewsDetailBinding
import com.example.local.dataSource.LocalDataSourceImpl
import com.example.presentation.viewModel.NewsDetailViewModel
import com.example.remote.dataSource.RemoteDataSourceImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint  //객체를 주입할 대상에게 선언 (Activity ,Fragment ,View ,Service ,BroadcastReceiver)
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(R.layout.fragment_news_detail){

    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    private val newsDetailViewModel: NewsDetailViewModel by viewModels ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewModel = newsDetailViewModel
        mBinding.lifecycleOwner = this

        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


    }
}