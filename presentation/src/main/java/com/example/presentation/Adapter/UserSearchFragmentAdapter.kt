package com.example.presentation.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.presentation.Model.GitUserModel
import com.example.presentation.Model.UserItemModel
import com.example.presentation.R

class UserSearchFragmentAdapter (
    private val mGlideRequestManager: RequestManager
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var gitUserModel : GitUserModel? = null
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener{
        fun clickUserLike(gitUserModel: UserItemModel)
        fun clickUserImg(gitUserModel: UserItemModel)
    }

    //외부에서 아이템 클릭 처리할 리스너
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setGitUser(gitSearchUser : GitUserModel) {
        this.gitUserModel = gitSearchUser
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val userInfoShow = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserInfoShowViewHolder(userInfoShow)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserInfoShowViewHolder).apply {
            this.bind(gitUserModel!!.items[position])
            ibFavorite.setOnClickListener {
                if(ibFavorite.resources.equals(R.drawable.heart_no)){
                    Log.d("click heart no","heart no")
                    ibFavorite.setImageResource(R.drawable.heart_ok)
                    onItemClickListener?.clickUserLike(gitUserModel!!.items[position])
                }
                else {
                    Log.d("click heart no","heart yes")
                    ibFavorite.setImageResource(R.drawable.heart_no)
                    onItemClickListener?.clickUserLike(gitUserModel!!.items[position])
                }
            }
            //이미지 클릭시 레포 화면으로 이동되게
            ivUser.setOnClickListener {
                onItemClickListener?.clickUserImg(gitUserModel!!.items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return gitUserModel?.items!!.size
    }

    inner class UserInfoShowViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var tvUserInfo : TextView = itemView.findViewById(R.id.tv_user_info)
        private var tvLink : TextView = itemView.findViewById(R.id.tv_link)
        var ivUser : ImageView = itemView.findViewById(R.id.iv_user)
        var ibFavorite : ImageButton = itemView.findViewById(R.id.ib_favorite)

        fun bind(gitUserModel: UserItemModel) {
            mGlideRequestManager.load(gitUserModel.avatar_url).into(ivUser)
            tvUserInfo.text = gitUserModel.login
            tvLink.text = gitUserModel.login
        }
    }

}