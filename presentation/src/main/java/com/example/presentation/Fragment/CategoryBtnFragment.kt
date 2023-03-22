package com.example.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentCategoryBtnBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint  //객체를 주입할 대상에게 선언 (Activity ,Fragment ,View ,Service ,BroadcastReceiver)
class CategoryBtnFragment : BaseFragment<FragmentCategoryBtnBinding>(R.layout.fragment_category_btn){

    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    var business = "business"
    var entertain = "entertain"
    var general = "general"
    var health = "health"
    var science = "science"
    var sports = "sports"
    var technology = "technology"




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

    }

    //해당 부분 작동하지 않음. 이유좀 달아주세요
    fun navigate(category : String){
        val item = bundleOf("category" to category)
        navController.navigate(R.id.categoryNewsFragment, item)
    }
}