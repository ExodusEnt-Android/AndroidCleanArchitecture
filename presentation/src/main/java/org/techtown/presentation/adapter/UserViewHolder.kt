package org.techtown.presentation.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.presentation.databinding.ItemUserBinding
import org.techtown.presentation.model.UserModel

class UserViewHolder(private val binding: ItemUserBinding,
                     private val context:Context ,
                     private val userClick: (UserModel, View, Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    interface onUserClickListener{
        fun onUserClick(model: UserModel, v: View, position: Int)
    }

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