package org.techtown.presentation.feature.main

import androidx.fragment.app.viewModels
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.local.feature.database.database.AppDatabase
import org.techtown.presentation.databinding.FragmentNewsDetailBinding
import org.techtown.local.feature.news.LocalDataSourceImpl
import org.techtown.remote.feature.news.RemoteDataSourceImpl
import org.techtown.data.repository.news.NewsRepository
import org.techtown.data.repository.news.NewsRepositoryImpl
import org.techtown.presentation.feature.main.viewmodel.NewsDetailViewmodel
import org.techtown.presentation.feature.main.viewmodel.factory.ViewModelFactory
import org.techtown.remote.retrofit.NewsService

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(R.layout.fragment_news_detail) {

    //db setting
    private val database: AppDatabase by lazy {
        AppDatabase.getInstance(requireActivity().applicationContext)
    }

    private val newsRepository: NewsRepository by lazy {
        val localDataSource = LocalDataSourceImpl(database)
        val remoteDataSource = RemoteDataSourceImpl(NewsService.apiService)
        NewsRepositoryImpl(localDataSource, remoteDataSource)
    }

    private val newsDetailViewModel : NewsDetailViewmodel by viewModels {
        ViewModelFactory(newsRepository = newsRepository)
    }

    override fun FragmentNewsDetailBinding.onCreateView() {
        initSet()
    }

    private fun initSet() {
        binding.viewModel = newsDetailViewModel
        binding.lifecycleOwner = this
    }

}