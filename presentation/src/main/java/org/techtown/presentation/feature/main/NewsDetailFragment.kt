package org.techtown.presentation.feature.main

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.databinding.FragmentNewsDetailBinding
import org.techtown.presentation.feature.main.viewmodel.NewsDetailViewmodel

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(R.layout.fragment_news_detail) {

    private val newsDetailViewModel: NewsDetailViewmodel by viewModels()

    override fun FragmentNewsDetailBinding.onCreateView() {
        initSet()
    }

    private fun initSet() {
        binding.viewModel = newsDetailViewModel
        binding.lifecycleOwner = this
    }

}