package org.techtown.presentation.feature.main

import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.local.feature.database.database.AppDatabase
import org.techtown.presentation.databinding.FragmentNewsDetailBinding
import org.techtown.local.feature.news.LocalDataSourceImpl
import org.techtown.remote.feature.news.RemoteDataSourceImpl
import org.techtown.presentation.model.Articles
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

    private val newsDetailViewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = ViewModelFactory(newsRepository = newsRepository)
        )[NewsDetailViewmodel::class.java]
    }

    override fun FragmentNewsDetailBinding.onCreateView() {
        getDataFromVM()
    }

    private fun getDataFromVM() {

        //UI세팅.
        newsDetailViewModel.initUI.observe(viewLifecycleOwner) { articles ->
            initSet(articles)
        }

        //해당 게시글 선택됨 여부 확인.
        newsDetailViewModel.isSelected.observe(viewLifecycleOwner) { isSelected ->
            setSavedItemListenerEvent(isSelected)
        }

        //게시글 저장 해제.
        newsDetailViewModel.isDeleted.observe(viewLifecycleOwner) { isDeleted ->
            if(isDeleted) {
                binding.ivSaved.setImageResource(R.drawable.star_inactive)
            }
        }

        //게시글 저장.
        newsDetailViewModel.isSaved.observe(viewLifecycleOwner) { isSaved ->
            if(isSaved) {
                binding.ivSaved.setImageResource(R.drawable.star_active)
            }

        }
    }

    private fun initSet(articles: Articles) {

        articles.let {

            binding.apply {

                tvTitle.text = articles.title

                tvName.text = articles.author

                Glide.with(requireActivity())
                    .load(articles.urlToImage)
                    .into(binding.ivNewsDetail)

                tvContent.text = articles.description
            }
        }
    }

    private fun setSavedItemListenerEvent(isSelected: Boolean) {
        if (isSelected) {
            binding.ivSaved.setImageResource(R.drawable.star_active)

            binding.ivSaved.setOnClickListener {
                newsDetailViewModel.deleteArticle()
            }
        } else {
            binding.ivSaved.setImageResource(R.drawable.star_inactive)

            binding.ivSaved.setOnClickListener {
               newsDetailViewModel.insertArticle()
            }
        }
    }

}