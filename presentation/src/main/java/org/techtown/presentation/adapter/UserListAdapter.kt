package org.techtown.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.techtown.presentation.databinding.ItemUserBinding
import org.techtown.presentation.model.PresentationUserModel


class UserListAdapter(
    private val userClick: ((PresentationUserModel, View, Int) -> Unit?)?,
    private val favClick: (PresentationUserModel, View, Int) -> Unit
) : ListAdapter<PresentationUserModel, UserViewHolder>(diffUtil) {

    interface onUserClickListener {
        fun onUserClick(model: PresentationUserModel, v: View, position: Int)
    }

    interface onFavClickListener {
        fun onFavClick(model: PresentationUserModel, v: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        //뷰바인딩 이용.
        val viewHolder = UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.apply {
            bind(getItem(position), position)
            itemView.setOnClickListener { v ->
                userClick?.invoke(getItem(position), v, position)
            }

            binding.ivStar.setOnClickListener { v ->
                favClick(getItem(position), v, position)
            }
        }
    }

    override fun submitList(list: List<PresentationUserModel>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PresentationUserModel>() {
            override fun areItemsTheSame(oldItem: PresentationUserModel, newItem: PresentationUserModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PresentationUserModel, newItem: PresentationUserModel) =
                oldItem == newItem

        }
    }

}