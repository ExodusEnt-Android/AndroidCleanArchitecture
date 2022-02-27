package com.example.gitsearchbook.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.gitsearchbook.Model.GitUserModel
import com.example.gitsearchbook.R

class UserFragmentAdapter(
    private val mGlideRequestManager: RequestManager
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var gitUserModel : ArrayList<GitUserModel> = ArrayList()
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener{


    }

    //외부에서 아이템 클릭 처리할 리스너
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setGitUserArray(gitUserArray : ArrayList<GitUserModel>) {
        this.gitUserModel = gitUserArray
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val userInfoShow = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserInfoShowViewHolder(userInfoShow)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("asdasd::","asdasd")

        (holder as UserInfoShowViewHolder).apply {
            this.bind(gitUserModel[position])
            ibFavorite.setOnClickListener {  }
        }
    }

    override fun getItemCount(): Int {
        return gitUserModel.size
    }

    inner class UserInfoShowViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var ivUser : ImageView = itemView.findViewById(R.id.iv_user)
        private var tvUserInfo : TextView = itemView.findViewById(R.id.tv_user_info)
        private var tvLink : TextView = itemView.findViewById(R.id.tv_link)
        var ibFavorite : ImageButton = itemView.findViewById(R.id.ib_favorite)

        fun bind(gitUserModel: GitUserModel) {
            mGlideRequestManager.load(gitUserModel.owner.avatar_url).into(ivUser)
            tvUserInfo.text = gitUserModel.owner.login
            tvLink.text = gitUserModel.owner.html_url
        }
    }

}