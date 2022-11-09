package com.example.presentation.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.*
import com.example.presentation.Adapter.NewsListAdapter
import com.example.presentation.Room.AppDB
import com.example.presentation.databinding.FragmentSavedBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedFragment : Fragment(R.layout.fragment_saved) , NewsListAdapter.OnClickListener{

    private lateinit var mBinding : FragmentSavedBinding
    private var saveNewsAdapter : NewsListAdapter? = null
    private lateinit var models : List<Articles>
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    private lateinit var newsDB : AppDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentSavedBinding.inflate(inflater, container, false)
        navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        newsDB = context?.let { AppDB.getInstance(it) }!!

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvTopNews.layoutManager = LinearLayoutManagerWrapper(requireContext(), LinearLayoutManager.VERTICAL, false)
        saveNewsAdapter = context?.let { NewsListAdapter(it, this) }
        mBinding.rvTopNews.adapter = saveNewsAdapter

        newsSource()
    }

    //저장된 뉴스 id를 통해 보여주기
    private fun newsSource() {
        CoroutineScope(Dispatchers.IO).launch {
            models = newsDB.newsDao().getAll()
            saveNewsAdapter?.setItems(models)
            Log.d("asdasdasd,",models.toString())
        }
        saveNewsAdapter?.notifyDataSetChanged() //여기다 선언하면 데이터 셋 되기전에 호출해서 바로 안뜸.
    }
    override fun onItemClicked(articles: Articles, view: View) {
        when(view.id){
            R.id.tv_author, R.id.tv_title, R.id.iv_photo -> {
                navController.navigate(R.id.newsDetailFragment,  Bundle().apply {
                    putParcelable("items", articles)
                })
            }
        }
    }
}