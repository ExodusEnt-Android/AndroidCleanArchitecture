package org.techtown.presentation.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.presentation.databinding.ItemUserBinding
import org.techtown.presentation.model.UserModel


class UserListAdapter(private val context: Context,
                      private val userClick: (UserModel, View, Int) -> Unit) : ListAdapter<UserModel, UserViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        //뷰바인딩 이용.
        val viewHolder = UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false), context) { userModel, view, i -> userClick(userModel, view, i) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Log.d("Response", "바인딩되었음")
        holder.bind(getItem(position), position)
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<UserModel>(){
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel) = oldItem == newItem

        }
    }

}