package com.example.presentation.fragment

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentCategoriesBinding

class CategoriesFragment:BaseFragment<FragmentCategoriesBinding>(R.layout.fragment_categories) {

    override fun FragmentCategoriesBinding.onCreateView() {
        setToolbar()
    }


    //toolbar setting
    private fun setToolbar(){
        binding.toolbar.tvTitle.text = requireActivity().getString(R.string.categories)
    }
}