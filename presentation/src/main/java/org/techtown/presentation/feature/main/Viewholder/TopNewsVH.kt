package org.techtown.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.techtown.presentation.databinding.TopNewsItemBinding
import org.techtown.presentation.feature.main.adapter.TopNewsItemListener
import org.techtown.presentation.model.Articles

class TopNewsVH(
    val binding: TopNewsItemBinding,
    private val clickListener: TopNewsItemListener
) : RecyclerView.ViewHolder(binding.root){

    fun bind(articles: Articles) {

        binding.article = articles
        binding.clickListener = clickListener
    }

}