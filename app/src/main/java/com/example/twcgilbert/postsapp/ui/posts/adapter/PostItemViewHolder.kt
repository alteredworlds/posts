package com.example.twcgilbert.postsapp.ui.posts.adapter

import android.databinding.ObservableField
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.twcgilbert.postsapp.common.ui.AdapterItemClick
import com.example.twcgilbert.postsapp.io.data.Post
import com.example.twcgilbert.postsapp.io.data.imageUrl

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostItemViewHolder(val binding: ViewDataBinding, val itemClick: AdapterItemClick?) :
        RecyclerView.ViewHolder(binding.root),
        PostItemContract {

    override val title: ObservableField<String> = ObservableField()

    override val userAvatarUrl = ObservableField<String>()

    override fun onClick(p0: View?) {
        itemClick?.onItemClick(adapterPosition)
    }

    fun update(postItem: Post) {
        title.set(postItem.title)
        userAvatarUrl.set(postItem.imageUrl)
    }
}