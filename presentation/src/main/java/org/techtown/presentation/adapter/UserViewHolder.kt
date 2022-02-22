package org.techtown.presentation.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.presentation.databinding.ItemUserBinding
import org.techtown.presentation.model.UserModel

class UserViewHolder(
    val binding: ItemUserBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: UserModel, position: Int) {
        //간단하게 유저 이미지 및 닉네임 넣어주기.
        Glide.with(context)
            .load(item.avatar_url)
            .into(binding.ivUserAvatar)
        binding.tvUserName.text = item.login

    }
}