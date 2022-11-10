package org.techtown.presentation

import android.os.Bundle

import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.databinding.FragmentTopNewsDetailBinding
import org.techtown.presentation.model.Articles


class TopNewsDetailFragment :
    BaseFragment<FragmentTopNewsDetailBinding>(R.layout.fragment_top_news_detail) {

    private lateinit var articles: Articles

    private var articleId: String = ""

    private lateinit var database: AppDatabase

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

        //db setting
        database = AppDatabase.getInstance(requireActivity().applicationContext)


        CoroutineScope(Dispatchers.IO).launch {
            val isSelected = database.articleDao().getAllArticles().any { it.url == articles.url }
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
                    database.articleDao().deleteArticle(articles.url)
                    binding.ivSaved.setImageResource(R.drawable.star_inactive)
                }
            }
        } else {
            binding.ivSaved.setImageResource(R.drawable.star_inactive)

            binding.ivSaved.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    database.articleDao().insertArticle(articles)
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