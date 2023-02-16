package org.techtown.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.presentation.databinding.TopNewsItemBinding
import org.techtown.presentation.model.Articles

class TopNewsVH(
    val binding: TopNewsItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(articles: Articles) {

        binding.article = articles
    }

}