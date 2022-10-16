package com.example.presentation.fragment

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentSavedBinding

class SavedFragment:BaseFragment<FragmentSavedBinding>(R.layout.fragment_saved) {
    override fun FragmentSavedBinding.onCreateView() {
        setToolbar()
    }

    //toolbar setting
    private fun setToolbar(){
        binding.toolbar.tvTitle.text = getString(R.string.saved)
    }
}