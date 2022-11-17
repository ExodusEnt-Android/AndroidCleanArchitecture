package org.techtown.presentation

import android.os.Bundle

import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.databinding.FragmentTopNewsDetailBinding
import org.techtown.presentation.datasource.local.NewsLocalDatasourceImpl
import org.techtown.presentation.datasource.remote.NewsRemoteDatasourceImpl
import org.techtown.presentation.model.Articles
import org.techtown.presentation.repository.NewsRepository
import org.techtown.presentation.repository.NewsRepositoryImpl
import org.techtown.presentation.retrofit.NewsService


class TopNewsDetailFragment :
    BaseFragment<FragmentTopNewsDetailBinding>(R.layout.fragment_top_news_detail) {

    private lateinit var articles: Articles

    private var articleId: String = ""

    //db setting
    private val database: AppDatabase by lazy {
        AppDatabase.getInstance(requireActivity().applicationContext)
    }

    private val newsRepository : NewsRepository by lazy {
        val newsRemoteDatasource = NewsRemoteDatasourceImpl(NewsService)
        val newsLocalDatasource = NewsLocalDatasourceImpl(database)
        NewsRepositoryImpl(newsRemoteDatasource, newsLocalDatasource)
    }


    override fun FragmentTopNewsDetailBinding.onCreateView() {
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
            val isSelected = newsRepository.getAllSavedArticles().any { it.url == articles.url }
            CoroutineScope((Dispatchers.Main)).launch {
                setSavedItemListenerEvent(isSelected)
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