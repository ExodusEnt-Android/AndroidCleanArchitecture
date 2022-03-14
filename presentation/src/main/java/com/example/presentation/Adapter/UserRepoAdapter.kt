package com.example.presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.presentation.Model.GitRepoModel
import com.example.presentation.ViewHolder.UserInfoShowViewHolder
import com.example.presentation.ViewHolder.UserRepoViewHolder
import com.example.presentation.databinding.UserItemBinding

class UserRepoAdapter(
    private val mGlideRequestManager: RequestManager
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var gitRepoModel : ArrayList<GitRepoModel> = ArrayList()
    private var onItemClickListener: OnItemClickListener? = null
    private var binding : UserItemBinding? = null

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
        binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserRepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserRepoViewHolder).apply {
            this.bind(mGlideRequestManager, gitRepoModel[position], binding)
            //즐겨찾기 클릭 시 처리
            binding?.ibFavorite?.setOnClickListener {

            }
        }
    }

    override fun getItemCount(): Int {
        return gitRepoModel.size
    }


}