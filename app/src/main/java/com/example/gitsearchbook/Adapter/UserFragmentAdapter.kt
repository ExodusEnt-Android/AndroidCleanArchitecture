package com.example.gitsearchbook.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitsearchbook.Model.GitUserModel
import com.example.gitsearchbook.R

class UserFragmentAdapter(
    gitUserModel: GitUserModel?
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val userInfoShow = LayoutInflater.from(parent.context).inflate(R.layout.fragment_user, parent, false)
        return UserInfoShowViewHolder(userInfoShow)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserInfoShowViewHolder).apply {
            this.bind()
            ibFavorite.setOnClickListener {  }
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class UserInfoShowViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var ivUser : ImageView = itemView.findViewById(R.id.iv_user)
        private var tvUserInfo : TextView = itemView.findViewById(R.id.tv_user_info)
        var ibFavorite : ImageButton = itemView.findViewById(R.id.ib_favorite)

        fun bind(){

        }
    }

}