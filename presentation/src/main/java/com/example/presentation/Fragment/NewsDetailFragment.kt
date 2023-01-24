package com.example.presentation.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.presentation.Articles
import com.example.presentation.R
import com.example.presentation.Room.AppDB
import com.example.presentation.databinding.FragmentNewsDetailBinding
import com.example.presentation.datasource.local.LocalDataSourceImpl
import com.example.presentation.datasource.remote.RemoteDataSourceImpl
import com.example.presentation.repository.NewsRepository
import com.example.presentation.repository.NewsRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(R.layout.fragment_news_detail), View.OnClickListener {

    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    private var ivSaved : Boolean = false
    var articles: Articles? = null

    private val newsDetailFragmentRepository : NewsRepository by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl(context?.let { AppDB.getInstance(it) }!!)

        NewsRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        articles = arguments?.getParcelable("items")
        Log.d("asdasd", articles.toString())
        mBinding.tvTitle.text = articles?.title
        mBinding.tvAuthor.text = articles?.author
        mBinding.tvDetail.text = articles?.description
        Glide.with(this).load(articles?.urlToImage).into(mBinding.ivPhoto)
        mBinding.ivSaved.setOnClickListener(this)

        CoroutineScope(Dispatchers.IO).launch {
            newsDetailFragmentRepository.getAll().collect{ it ->
                if(it.any { it.url == articles!!.url }){
                    mBinding.ivSaved.setImageResource(R.drawable.star_ok)
                }else{
                    mBinding.ivSaved.setImageResource(R.drawable.star_no)
                }
            }
        }
    }

    override fun onClick(view: View) {
        when(view){
            mBinding.ivSaved -> {
                if(ivSaved){
                    ivSaved = false
                    CoroutineScope(Dispatchers.IO).launch {
                        mBinding.ivSaved.setImageResource(R.drawable.star_no)
                        newsDetailFragmentRepository.deleteArticle(articles!!.url){}
                    }
                }else{
                    ivSaved = true
                    CoroutineScope(Dispatchers.IO).launch {
                        mBinding.ivSaved.setImageResource(R.drawable.star_ok)
                        newsDetailFragmentRepository.insert(articles!!){}
                    }
                }
            }
        }
    }
}