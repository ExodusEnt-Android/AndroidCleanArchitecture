package org.techtown.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.techtown.presentation.databinding.ItemUserBinding
import org.techtown.presentation.model.UserModel


class FavoriteListAdapter(
    private val context: Context,
) : ListAdapter<UserModel, UserViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        //뷰바인딩 이용.
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), context
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.apply {
            bind(getItem(position), position)
            binding.ivStar.visibility = View.GONE
        }
    }

    override fun submitList(list: List<UserModel>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel) =
                oldItem == newItem

        }
    }

}