package org.techtown.presentation.feature.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.techtown.presentation.R
import org.techtown.presentation.databinding.ItemLoadingBinding
import org.techtown.presentation.databinding.TopNewsItemBinding
import org.techtown.presentation.model.Articles
import org.techtown.presentation.viewholder.LoadingVH
import org.techtown.presentation.viewholder.TopNewsVH

class TopNewsAdapter :
    ListAdapter<Articles, RecyclerView.ViewHolder>(diffUtil) {

    private var onItemClickListener: ItemClickListener? = null

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.onItemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(articles: Articles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemTopNews: TopNewsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.top_news_item,
            parent,
            false
        )

        return when (viewType) {
            IMAGE_ARTICLE -> {
                TopNewsVH(itemTopNews).apply {
                    itemView.setOnClickListener {
                        onItemClickListener?.onItemClick(currentList[bindingAdapterPosition])
                    }
                }
            }
            else -> {
                val itemLoading: ItemLoadingBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_loading,
                    parent,
                    false
                )
                LoadingVH(itemLoading)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            IMAGE_ARTICLE -> {
                (holder as TopNewsVH).apply {
                    bind(currentList[bindingAdapterPosition])
                    binding.executePendingBindings()
                }
            }
            else -> {
                (holder as LoadingVH)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].isLoading) {
            BOTTOM_LOADING_TYPE
        } else {
            IMAGE_ARTICLE
        }
    }

    fun deleteLoading() {
        try {

            if (currentList[currentList.lastIndex].isLoading) {
                Log.d(
                    "DeleteLoading",
                    "${currentList[currentList.lastIndex].isLoading} ${currentList.lastIndex}"
                )
                val lastIndex = currentList.lastIndex
                val newList = currentList.toMutableList()
                newList.removeAt(lastIndex)// 로딩이 완료되면 프로그레스바를 지움
                submitList(newList.map { it.copy() })
                Log.d("DeleteLoading", "${newList[newList.lastIndex].title}")
            }
        } catch (e: Exception) {

        }
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].hashCode().toLong()
    }

    companion object {

        const val IMAGE_ARTICLE = 1
        const val BOTTOM_LOADING_TYPE = 2//가장 아래  로딩

        val diffUtil = object : DiffUtil.ItemCallback<Articles>() {
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(
                oldItem: Articles,
                newItem: Articles
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


}