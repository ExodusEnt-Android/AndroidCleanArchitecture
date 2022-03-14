package com.example.presentation.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.presentation.Model.GitRepoModel
import com.example.presentation.databinding.UserItemBinding

class UserRepoViewHolder(itemView: UserItemBinding?) : RecyclerView.ViewHolder(itemView?.root!!){

    fun bind(mGlideRequestManager: RequestManager?,  gitRepoModel: GitRepoModel, binding: UserItemBinding?) {
        mGlideRequestManager?.load(gitRepoModel.owner.avatar_url)?.into(binding?.ivUser!!)
        binding?.tvUserInfo?.text = gitRepoModel.owner.login
        binding?.tvLink?.text = gitRepoModel.owner.html_url
    }
}