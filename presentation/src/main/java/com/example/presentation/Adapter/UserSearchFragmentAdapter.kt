package com.example.presentation.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.presentation.Model.GitUserModel
import com.example.presentation.Model.UserItemModel
import com.example.presentation.R
import com.example.presentation.ViewHolder.UserInfoShowViewHolder
import com.example.presentation.databinding.UserItemBinding

class UserSearchFragmentAdapter (
    private val mGlideRequestManager: RequestManager
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var gitUserModel : GitUserModel? = null
    private var onItemClickListener: OnItemClickListener? = null
    private var binding : UserItemBinding?= null

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
        binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserInfoShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserInfoShowViewHolder).apply {
            this.bind(mGlideRequestManager , gitUserModel!!.items[position], binding)
            binding?.ibFavorite?.setOnClickListener {
                if(binding?.ibFavorite?.resources?.equals(R.drawable.heart_no) == true){
                    Log.d("click heart no","heart no")
                    binding?.ibFavorite?.setImageResource(R.drawable.heart_ok)
                    onItemClickListener?.clickUserLike(gitUserModel!!.items[position])
                }
                else {
                    Log.d("click heart no","heart yes")
                    binding?.ibFavorite?.setImageResource(R.drawable.heart_no)
                    onItemClickListener?.clickUserLike(gitUserModel!!.items[position])
                }
            }
            //이미지 클릭시 레포 화면으로 이동되게
            binding?.ivUser?.setOnClickListener {
                onItemClickListener?.clickUserImg(gitUserModel!!.items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return gitUserModel?.items!!.size
    }


}