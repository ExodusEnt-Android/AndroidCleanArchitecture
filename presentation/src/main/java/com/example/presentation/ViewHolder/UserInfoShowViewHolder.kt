package com.example.presentation.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.presentation.Model.UserItemModel
import com.example.presentation.databinding.UserItemBinding

class UserInfoShowViewHolder(itemView: UserItemBinding?) : RecyclerView.ViewHolder(itemView?.root!!){

    fun bind(
        mGlideRequestManager: RequestManager?,
        gitUserModel: UserItemModel, binding: UserItemBinding?
    ) {
        mGlideRequestManager?.load(gitUserModel.avatar_url)?.into(binding!!.ivUser)
        binding!!.tvUserInfo.text = gitUserModel.login
        binding.tvLink.text = gitUserModel.login
    }
}