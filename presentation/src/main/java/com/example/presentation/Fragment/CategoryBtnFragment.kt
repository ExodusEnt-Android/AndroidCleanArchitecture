package com.example.presentation.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.presentation.*
import com.example.presentation.Adapter.CategoryAdapter
import com.example.presentation.databinding.FragmentCategoryBtnBinding

class CategoryBtnFragment : Fragment(R.layout.fragment_category_btn) , View.OnClickListener{

    private lateinit var mBinding : FragmentCategoryBtnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentCategoryBtnBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnBusiness.setOnClickListener(this)
        mBinding.btnEntertain.setOnClickListener(this)
        mBinding.btnGeneral.setOnClickListener(this)
        mBinding.btnHealth.setOnClickListener(this)
        mBinding.btnScience.setOnClickListener(this)
        mBinding.btnSports.setOnClickListener(this)
        mBinding.btnTech.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_business -> {
                val item = bundleOf("category" to "business")
                mBinding.btnBusiness.findNavController().navigate(R.id.action_category_news, item)
            }
            R.id.btn_entertain -> {
                val item = bundleOf("category" to "business")
                mBinding.btnBusiness.findNavController().navigate(R.id.action_category_news, item)
            }
            R.id.btn_general -> {
                val item = bundleOf("category" to "general")
                mBinding.btnBusiness.findNavController().navigate(R.id.action_category_news, item)
            }
            R.id.btn_health -> {
                val item = bundleOf("category" to "health")
                mBinding.btnBusiness.findNavController().navigate(R.id.action_category_news, item)
            }
            R.id.btn_science -> {
                val item = bundleOf("category" to "science")
                mBinding.btnBusiness.findNavController().navigate(R.id.action_category_news, item)
            }
            R.id.btn_sports -> {
                val item = bundleOf("category" to "sports")
                mBinding.btnBusiness.findNavController().navigate(R.id.action_category_news, item)
            }
            R.id.btn_tech -> {
                val item = bundleOf("category" to "technology")
                mBinding.btnBusiness.findNavController().navigate(R.id.action_category_news, item)
            }
        }
    }
}