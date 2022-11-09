package com.example.presentation.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.Base.BaseActivity
import com.example.presentation.Fragment.CategoryBtnFragment
import com.example.presentation.Fragment.SavedFragment
import com.example.presentation.Fragment.TopNewsFragment
import com.example.presentation.R
import com.example.presentation.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var mBinding : ActivityMainBinding

    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initNavigationBar()
    }

    private fun initNavigationBar() {

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        mBinding.bottomNav.setupWithNavController(navController)


    }


}