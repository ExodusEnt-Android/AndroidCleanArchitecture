/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.model.PresentationArticles

//리사이클러뷰에
@BindingAdapter("bind:addList")
fun RecyclerView.addList(list: List<PresentationArticles>?) {
    list?.toMutableList()?.let { (this.adapter as NewsListAdapter).setItems(it) }
}


@BindingAdapter("bind:loadImage")
fun ImageView.loadImage(image:String?){
    Glide.with(this.context)
        .load(image)
        .placeholder(R.drawable.star_ok)
        .into(this)
}