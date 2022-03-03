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
import com.example.gitsearchbook.Model.GitRepoModel
import com.example.gitsearchbook.R

class UserDetailFragmentAdapter(
    private val mGlideRequestManager: RequestManager
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var gitRepoModel : ArrayList<GitRepoModel> = ArrayList()
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener{
        fun clickUserLike(gitRepoModel: GitRepoModel)
    }

    //외부에서 아이템 클릭 처리할 리스너
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setGitUserArray(gitRepoArray : ArrayList<GitRepoModel>) {
        this.gitRepoModel = gitRepoArray
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val userInfoShow = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserInfoShowViewHolder(userInfoShow)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserInfoShowViewHolder).apply {
            this.bind(gitRepoModel[position])
            ibFavorite.setOnClickListener {
                if(ibFavorite.resources.equals(R.drawable.heart_no)){
                    Log.d("click heart no","heart no")
                    ibFavorite.setImageResource(R.drawable.heart_ok)
                    onItemClickListener?.clickUserLike(gitRepoModel[position])
                }
                else {
                    Log.d("click heart no","heart yes")
                    ibFavorite.setImageResource(R.drawable.heart_no)
                    onItemClickListener?.clickUserLike(gitRepoModel[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return gitRepoModel.size
    }

    inner class UserInfoShowViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var ivUser : ImageView = itemView.findViewById(R.id.iv_user)
        private var tvUserInfo : TextView = itemView.findViewById(R.id.tv_user_info)
        private var tvLink : TextView = itemView.findViewById(R.id.tv_link)
        var ibFavorite : ImageButton = itemView.findViewById(R.id.ib_favorite)

        fun bind(gitRepoModel: GitRepoModel) {
            mGlideRequestManager.load(gitRepoModel.owner.avatar_url).into(ivUser)
            tvUserInfo.text = gitRepoModel.owner.login
            tvLink.text = gitRepoModel.owner.html_url
        }
    }

}