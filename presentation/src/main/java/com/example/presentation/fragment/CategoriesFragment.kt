package com.example.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.const.Const
import com.example.presentation.databinding.FragmentCategoriesBinding
import com.example.presentation.enum.Category
import com.example.presentation.util.Util.navigateWithAnim

class CategoriesFragment:BaseFragment<FragmentCategoriesBinding>(R.layout.fragment_categories) {

    //네비게이션 컨트롤러
    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    override fun FragmentCategoriesBinding.onCreateView() {
        initSet()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEventListener()
    }
    private fun initSet(){

        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

        setToolbar()
    }

    private fun setEventListener(){

        //category 별  버튼 클릭 리스너 달아줌.
        Category.values().forEach { category->
            view?.findViewById<AppCompatTextView>(category.viewId)?.setOnClickListener {
                navController.navigateWithAnim(R.id.categoryTopNewsFragment, Bundle().apply {
                    putString(Const.PARAM_ARTICLE_CATEGORY,category.queryString)//선택한 카테고리 보냄.
                })
            }
        }
    }




    //toolbar setting
    private fun setToolbar(){
        binding.toolbar.tvTitle.text = requireActivity().getString(R.string.categories)
    }
}