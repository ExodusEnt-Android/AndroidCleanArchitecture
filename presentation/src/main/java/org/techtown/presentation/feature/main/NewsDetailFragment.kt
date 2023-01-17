package org.techtown.presentation.feature.main

import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.databinding.FragmentNewsDetailBinding
import org.techtown.presentation.model.Articles
import org.techtown.presentation.repository.NewsRepository
import org.techtown.presentation.repository.NewsRepositoryImpl
import org.techtown.presentation.retrofit.NewsService
import kotlin.concurrent.thread

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(R.layout.fragment_news_detail) {

    private lateinit var articles: Articles

    private var articleId: String = ""

    //db setting
    private val database: AppDatabase by lazy {
        AppDatabase.getInstance(requireActivity().applicationContext)
    }

    private val newsRepository: NewsRepository by lazy {
        val newsService = NewsService.apiService
        NewsRepositoryImpl(newsService, database)
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

        CoroutineScope(Dispatchers.IO).launch {
            newsRepository.getAllSavedArticles().collect { data ->
                val isSelected = data.any { it.url == articles.url }
                withContext(Dispatchers.Main) {
                    setSavedItemListenerEvent(isSelected)
                }
            }
        }
    }

    private fun setSavedItemListenerEvent(isSelected: Boolean) {
        if (isSelected) {
            binding.ivSaved.setImageResource(R.drawable.star_active)

            binding.ivSaved.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    newsRepository.deleteArticle(articles.url) {
                        CoroutineScope(Dispatchers.Main).launch {
                            binding.ivSaved.setImageResource(R.drawable.star_inactive)
                        }
                    }
                }
            }
        } else {
            binding.ivSaved.setImageResource(R.drawable.star_inactive)

            binding.ivSaved.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    newsRepository.insertArticle(articles) {
                        CoroutineScope(Dispatchers.Main).launch {
                            binding.ivSaved.setImageResource(R.drawable.star_active)
                        }
                    }
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