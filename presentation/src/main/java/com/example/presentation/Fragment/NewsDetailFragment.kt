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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsDetailFragment : Fragment(R.layout.fragment_news_detail), View.OnClickListener {

    private lateinit var mBinding : FragmentNewsDetailBinding
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    private var ivSaved : Boolean = false
    private lateinit var newsDB : AppDB

    var articles: Articles? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        newsDB = context?.let { AppDB.getInstance(it) }!!

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articles = arguments?.getParcelable("items")
        Log.d("asdasd", articles.toString())
        mBinding.tvTitle.text = articles?.title
        mBinding.tvAuthor.text = articles?.author
        mBinding.tvDetail.text = articles?.description
        Glide.with(this).load(articles?.urlToImage).into(mBinding.ivPhoto)
        mBinding.ivSaved.setOnClickListener(this)

        CoroutineScope(Dispatchers.IO).launch {
            if(newsDB.newsDao().getAll().any { items -> items.url == articles!!.url  }){    //같은 url이 있을 경우, 즐겨찾기를 해놨단 이야기이므로
                mBinding.ivSaved.setImageResource(R.drawable.star_ok)
            }else{
                mBinding.ivSaved.setImageResource(R.drawable.star_no)
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
                        articles?.let { newsDB.newsDao().deleteArticle(articles!!.url) }   //DB delete
                    }
                }else{
                    ivSaved = true
                    CoroutineScope(Dispatchers.IO).launch {
                        mBinding.ivSaved.setImageResource(R.drawable.star_ok)
                        articles?.let { newsDB.newsDao().insert(it) }   //DB에 INSERT
                    }
                }
            }
        }
    }
}