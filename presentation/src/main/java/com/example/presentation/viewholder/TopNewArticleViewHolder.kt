package com.example.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.databinding.ItemNewsListBinding
import com.example.presentation.model.Article
import com.example.presentation.util.Util.checkTimePassed
import timber.log.Timber

class TopNewArticleViewHolder(
    val binding:ItemNewsListBinding
):RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article){
        //썸네일 이미지 적용
        Glide.with(itemView.context)
            .load(article.urlToImage)
            .into(binding.ivNewsThumbnail)

        binding.tvAuthor.text = article.author?:"작자 미상"
        binding.tvNewsTitle.text = article.title?:"기사 제목 없음"
        binding.tvPublishTime.text = article.publishedAt.checkTimePassed()
    }
}