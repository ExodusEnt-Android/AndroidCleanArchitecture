package com.example.presentation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.model.Articles
import com.example.presentation.R
import com.example.presentation.databinding.NewsItemBinding
import com.example.presentation.model.PresentationArticles
import java.util.ArrayList

class NewsListAdapter (
    private val context: Context,
    private var onClickListener: OnClickListener
)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var newsData = ArrayList<PresentationArticles>()

    interface OnClickListener{
        fun onItemClicked(item : PresentationArticles, view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : NewsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_item,
            parent,
            false
        )
        return NewsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsListViewHolder).bind(newsData[position])
    }

    override fun getItemCount(): Int = newsData.size

    fun setItems(@NonNull items: List<PresentationArticles>) {
        newsData.clear()
        newsData.addAll(items)
        notifyDataSetChanged()
    }

    inner class NewsListViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : PresentationArticles){
            Glide.with(itemView).load(item.urlToImage).into(binding.ivPhoto)
            binding.tvTitle.text = item.title
            binding.tvAuthor.text = item.author

            val listener = View.OnClickListener { view ->
                onClickListener.onItemClicked(item, view) }
            binding.clArticle.setOnClickListener(listener)
        }
    }
}