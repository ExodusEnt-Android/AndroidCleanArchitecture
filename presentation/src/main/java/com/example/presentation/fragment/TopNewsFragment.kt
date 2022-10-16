package com.example.presentation.fragment

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentTopNewsBinding

class TopNewsFragment:BaseFragment<FragmentTopNewsBinding>(R.layout.fragment_top_news) {
    override fun FragmentTopNewsBinding.onCreateView() {
        setToolbar()
    }

    //toolbar setting
    private fun setToolbar(){
        binding.toolbar.tvTitle.text = requireActivity().getString(R.string.top_news)
    }
}