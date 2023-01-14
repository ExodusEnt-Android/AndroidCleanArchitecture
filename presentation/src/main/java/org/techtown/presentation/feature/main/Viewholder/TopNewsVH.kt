package org.techtown.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.androidcleanarchitecturecoroutine.databinding.TopNewsItemBinding
import org.techtown.presentation.model.Articles

class TopNewsVH(
    val binding: TopNewsItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(articles: Articles) {

        //아이템 타이틀.
        binding.tvTopNewsTitle.text = articles.content

        //아이템 이미지.
        Glide.with(itemView)
            .load(articles.urlToImage)
            .into(binding.ivTopNews)
    }

}