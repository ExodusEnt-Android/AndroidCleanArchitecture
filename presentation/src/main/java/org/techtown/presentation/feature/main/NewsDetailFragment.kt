package org.techtown.presentation.feature.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.databinding.FragmentNewsDetailBinding
import org.techtown.presentation.datasource.local.LocalDataSourceImpl
import org.techtown.presentation.datasource.remote.RemoteDataSourceImpl
import org.techtown.presentation.model.Articles
import org.techtown.presentation.repository.NewsRepository
import org.techtown.presentation.repository.NewsRepositoryImpl
import org.techtown.presentation.retrofit.NewsService

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(R.layout.fragment_news_detail) {

    private lateinit var articles: Articles

    private var articleId: String = ""

    //db setting
    private val database: AppDatabase by lazy {
        AppDatabase.getInstance(requireActivity().applicationContext)
    }

    private val newsRepository: NewsRepository by lazy {
        val localDataSource = LocalDataSourceImpl(database)
        val remoteDataSource = RemoteDataSourceImpl(NewsService.apiService)
        NewsRepositoryImpl(localDataSource, remoteDataSource)
    }

    override fun FragmentNewsDetailBinding.onCreateView() {
        initSet()
    }

    private fun initSet() {

        articles.let {

            binding.apply {

                //ID가 String값임.
                articleId = articles.source?.id ?: ""

                tvTitle.text = articles.title

                tvName.text = articles.author

                Glide.with(requireActivity())
                    .load(articles.urlToImage)
                    .into(binding.ivNewsDetail)

                tvContent.text = articles.description
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            newsRepository.getAllArticles().collect { data ->
                val isSelected = data.any { it.url == articles.url }
                setSavedItemListenerEvent(isSelected)
            }
        }
    }

    private fun setSavedItemListenerEvent(isSelected: Boolean) {
        if (isSelected) {
            binding.ivSaved.setImageResource(R.drawable.star_active)

            binding.ivSaved.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    newsRepository.deleteArticle(articles.url)
                    binding.ivSaved.setImageResource(R.drawable.star_inactive)
                }
            }
        } else {
            binding.ivSaved.setImageResource(R.drawable.star_inactive)

            binding.ivSaved.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    newsRepository.insertArticle(articles)
                    binding.ivSaved.setImageResource(R.drawable.star_active)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articles = it.getParcelable("top_news_detail") ?: Articles(title = "", url = "")
        }
    }

}