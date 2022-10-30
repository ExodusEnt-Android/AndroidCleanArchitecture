package com.example.presentation.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.*
import com.example.presentation.Adapter.CategoryAdapter
import com.example.presentation.databinding.FragmentCategoryNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryNewsFragment : Fragment(R.layout.fragment_category_news), CategoryAdapter.OnClickListener{

    private lateinit var mBinding : FragmentCategoryNewsBinding
    private var categoryAdapter : CategoryAdapter? = null
    private lateinit var models : ArrayList<Items>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentCategoryNewsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvCategory.layoutManager = LinearLayoutManagerWrapper(requireContext(), LinearLayoutManager.VERTICAL, false)
        categoryAdapter = context?.let { CategoryAdapter(it, this) }
        mBinding.rvCategory.adapter = categoryAdapter

        val category = arguments?.getString("category")
        if (category != null) {
            newsCategory(category)
        }
    }

    private fun newsCategory(category : String) {
        val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build();
        val service = retrofit.create(ApiService::class.java)

        service.requestCategoryNews(category).enqueue(object : Callback<NewsData> {
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                if(response.isSuccessful){
                    // 정상적으로 통신이 성공된 경우
                    val result: NewsData? = response.body()
                    val model = result?.articles

                    models = ArrayList()
                    for(i in model!!.indices){
                        models.add(model[i])
                    }

                    categoryAdapter?.setItems(models)
                    categoryAdapter?.notifyDataSetChanged()
                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("mingue ", "onResponse 실패$response")
                }
            }
            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure 에러: " + t.message.toString());
            }
        })
    }

    override fun onItemClicked(item: Items, view: View) {
        when(view.id){
            R.id.tv_author -> {
                val bundle = bundleOf("title" to item.title, "author" to item.author, "desc" to item.description , "image" to item.urlToImage)
                findNavController().navigate(R.id.action_category_detail, bundle)
            }
            R.id.tv_title -> {
                val bundle = bundleOf("title" to item.title, "author" to item.author, "desc" to item.description , "image" to item.urlToImage)
                findNavController().navigate(R.id.action_category_detail, bundle)
            }
        }
    }
}