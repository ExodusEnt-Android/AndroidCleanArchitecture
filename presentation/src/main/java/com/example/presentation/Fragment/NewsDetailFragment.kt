package com.example.presentation.Fragment

import ViewModelFactory
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.data.repository.NewsRepository
import com.example.data.repository.NewsRepositoryImpl
import com.example.presentation.R
import com.example.local.Room.AppDB
import com.example.presentation.databinding.FragmentNewsDetailBinding
import com.example.local.dataSource.LocalDataSourceImpl
import com.example.presentation.ViewModel.NewsDetailViewModel
import com.example.presentation.model.PresentationArticles
import com.example.remote.dataSource.RemoteDataSourceImpl
import timber.log.Timber

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(R.layout.fragment_news_detail), View.OnClickListener {

    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    private var isSaved : Boolean = false
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

        getDataFromVM()
    }

    private val newsDetailViewModel: NewsDetailViewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = ViewModelFactory(repository = newsDetailFragmentRepository)
        )[NewsDetailViewModel::class.java]
    }


    private fun getDataFromVM(){
        newsDetailViewModel.isSaved.observe(viewLifecycleOwner) {
            if (it) {
                isSaved = true
                mBinding.ivSaved.setImageResource(R.drawable.star_ok)
            } else {
                isSaved = false
                mBinding.ivSaved.setImageResource(R.drawable.star_no)
            }
        }
    }

    override fun onClick(view: View) {
        when(view){
            mBinding.ivSaved -> {
                if(isSaved){
                    newsDetailViewModel.deleteArticle()

                }else{
                    newsDetailViewModel.saveArticle()
                }
            }
        }
    }
}