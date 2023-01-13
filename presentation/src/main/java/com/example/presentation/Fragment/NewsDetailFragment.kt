package com.example.presentation.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import com.example.presentation.Room.AppDB
import com.example.presentation.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment(R.layout.fragment_news_detail), View.OnClickListener {
    private lateinit var mBinding : FragmentNewsDetailBinding
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    private var ivSaved : Boolean = false
    private lateinit var newsDB : AppDB


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        newsDB = context?.let { AppDB.getInstance(it) }!!

        return mBinding.root
    }


    override fun onClick(p0: View?) {

    }
}