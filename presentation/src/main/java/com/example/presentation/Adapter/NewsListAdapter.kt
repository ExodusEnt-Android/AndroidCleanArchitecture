package com.example.presentation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.Articles
import com.example.presentation.R
import java.util.ArrayList

class NewsListAdapter (
    private val context: Context,
    private var onClickListener: OnClickListener
)  : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private var newsData = ArrayList<Articles>()

    interface OnClickListener{
        fun onItemClicked(item : Articles, view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsData[position])
    }

    override fun getItemCount(): Int = newsData.size

    fun setItems(@NonNull items: List<Articles>) {
        newsData.clear()
        newsData.addAll(items)
    }

    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view){

        private val ivPhoto : AppCompatImageView = itemView.findViewById(R.id.iv_photo)
        private val tvTitle : AppCompatTextView = itemView.findViewById(R.id.tv_title)
        private val tvAuthor : AppCompatTextView = itemView.findViewById(R.id.tv_author)

        fun bind(item : Articles){
            Glide.with(itemView).load(item.urlToImage).into(ivPhoto)
            tvTitle.text = item.title
            tvAuthor.text = item.author

            val listener = View.OnClickListener { view ->
                onClickListener.onItemClicked(item, view) }

            tvTitle.setOnClickListener(listener)
            tvAuthor.setOnClickListener(listener)
            ivPhoto.setOnClickListener(listener)
        }
    }
}