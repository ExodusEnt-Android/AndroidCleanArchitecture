package org.techtown.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.presentation.R
import org.techtown.presentation.databinding.ItemUserBinding
import org.techtown.presentation.model.UserModel

class UserViewHolder(
    val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: UserModel, position: Int) {
        //간단하게 유저 이미지 및 닉네임 넣어주기.
        Glide.with(itemView.context)
            .load(item.avatar_url)
            .into(binding.ivUserAvatar)
        binding.tvUserName.text = item.login

        binding.ivStar.setBackgroundResource(if (item.is_favorite) R.drawable.star_selected_36 else R.drawable.star_unselected_36)

    }
}