package com.example.presentation.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.*
import com.example.presentation.Base.BaseFragment
import com.example.presentation.databinding.FragmentCategoryBtnBinding

class CategoryBtnFragment : Fragment(R.layout.fragment_category_btn) , View.OnClickListener{

    private lateinit var mBinding : FragmentCategoryBtnBinding
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentCategoryBtnBinding.inflate(inflater, container, false)
        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


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
                navController.navigate(R.id.categoryNewsFragment, item)
            }
            R.id.btn_entertain -> {
                val item = bundleOf("category" to "business")
                navController.navigate(R.id.categoryNewsFragment, item)
            }
            R.id.btn_general -> {
                val item = bundleOf("category" to "general")
                navController.navigate(R.id.categoryNewsFragment, item)
            }
            R.id.btn_health -> {
                val item = bundleOf("category" to "health")
                navController.navigate(R.id.categoryNewsFragment, item)
            }
            R.id.btn_science -> {
                val item = bundleOf("category" to "science")
                navController.navigate(R.id.categoryNewsFragment, item)
            }
            R.id.btn_sports -> {
                val item = bundleOf("category" to "sports")
                navController.navigate(R.id.categoryNewsFragment, item)
            }
            R.id.btn_tech -> {
                val item = bundleOf("category" to "technology")
                navController.navigate(R.id.categoryNewsFragment, item)
            }
        }
    }
}