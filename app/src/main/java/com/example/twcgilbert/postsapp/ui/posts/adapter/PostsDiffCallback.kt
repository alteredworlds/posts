package com.example.twcgilbert.postsapp.ui.posts.adapter

import android.support.v7.util.DiffUtil
import com.example.twcgilbert.postsapp.repo.model.Post

class PostsDiffCallback(
        private val newItems: List<Post>,
        private val oldItems: List<Post>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition] == newItems[newItemPosition]
}