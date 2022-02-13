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
                      private val userClick: (UserModel, View, Int) -> Unit) : ListAdapter<UserModel, UserListAdapter.ViewHolder>(diffUtil) {

    interface onUserClickListener{
        fun onUserClick(model: UserModel, v:View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        //뷰바인딩 이용.
        val viewHolder = ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "바인딩되었음")
        holder.bind(getItem(position), position)
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(item: UserModel, position: Int){
            //간단하게 유저 이미지 및 닉네임 넣어주기.
            Glide.with(context)
                .load(item.avatar_url)
                .into(binding.ivUserAvatar)
            binding.tvUserName.text = item.login

            //유저 클릭 추가.
            binding.clUser.setOnClickListener { v ->
                userClick(item,v , position)
            }
        }
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<UserModel>(){
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel) = oldItem == newItem

        }
    }

}