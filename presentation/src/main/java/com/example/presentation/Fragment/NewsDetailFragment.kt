package com.example.presentation.Fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.data.repository.NewsRepository
import com.example.data.repository.NewsRepositoryImpl
import com.example.presentation.R
import com.example.local.Room.AppDB
import com.example.presentation.databinding.FragmentNewsDetailBinding
import com.example.local.dataSource.LocalDataSourceImpl
import com.example.presentation.model.PresentationArticles
import com.example.presentation.model.PresentationArticles.Companion.toData
import com.example.remote.dataSource.RemoteDataSourceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(R.layout.fragment_news_detail), View.OnClickListener {

    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    private var ivSaved : Boolean = false
    var articles: PresentationArticles? = null

    private val newsDetailFragmentRepository : NewsRepository by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl(AppDB.getInstance(requireActivity()))

        NewsRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        articles = arguments?.getParcelable("items")
        mBinding.tvTitle.text = articles?.title
        mBinding.tvAuthor.text = articles?.author
        mBinding.tvDetail.text = articles?.description
        Glide.with(this).load(articles?.urlToImage).into(mBinding.ivPhoto)
        mBinding.ivSaved.setOnClickListener(this)

        CoroutineScope(Dispatchers.Main).launch {
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
                        newsDetailFragmentRepository.deleteArticle(articles!!.url)
                    }
                }else{
                    ivSaved = true

                    CoroutineScope(Dispatchers.Main).launch {
                        newsDetailFragmentRepository.insert(articles!!.toData())
                        mBinding.ivSaved.setImageResource(R.drawable.star_ok)

                    }
                }
            }
        }
    }
}