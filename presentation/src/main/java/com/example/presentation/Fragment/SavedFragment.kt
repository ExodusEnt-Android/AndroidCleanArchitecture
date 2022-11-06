package com.example.presentation.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.*
import com.example.presentation.Adapter.NewsListAdapter
import com.example.presentation.Room.NewsDB
import com.example.presentation.databinding.FragmentSavedBinding

class SavedFragment : Fragment(R.layout.fragment_saved) , NewsListAdapter.OnClickListener{

    private lateinit var mBinding : FragmentSavedBinding
    private var saveNewsAdapter : NewsListAdapter? = null
    private lateinit var models : ArrayList<Items>
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    val newsDB = context?.let { NewsDB.getInstance(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentSavedBinding.inflate(inflater, container, false)
        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvTopNews.layoutManager = LinearLayoutManagerWrapper(requireContext(), LinearLayoutManager.VERTICAL, false)
        saveNewsAdapter = context?.let { NewsListAdapter(it, this) }
        mBinding.rvTopNews.adapter = saveNewsAdapter

        newsSource("bbc-news")
    }

    //저장된 뉴스 id를 통해 보여주기
    private fun newsSource(primaryKey : String) {
        Thread{
            val newsList = newsDB?.newsDao()?.getAll()
            saveNewsAdapter?.setItems(newsList)
        }

    }

    override fun onItemClicked(item: Items, view: View) {
        when(view.id){
            R.id.tv_author, R.id.tv_title, R.id.iv_photo -> {
                val bundle = bundleOf("title" to item.title, "author" to item.author, "desc" to item.description , "image" to item.urlToImage)
                navController.navigate(R.id.newsDetailFragment, bundle)
            }
        }
    }
}