package org.techtown.presentation.feature.main.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import timber.log.Timber


@BindingAdapter("app:addList")
fun <T, VH : RecyclerView.ViewHolder> RecyclerView.addList(items: List<T>?) {
    (adapter as ListAdapter<T, VH>).submitList(items?.toMutableList())
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(image: String?) {
    //아이템 이미지.
    Glide.with(this.context)
        .load(image)
        .into(this)
}