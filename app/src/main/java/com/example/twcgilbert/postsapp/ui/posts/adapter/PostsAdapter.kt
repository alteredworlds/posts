package com.example.twcgilbert.postsapp.ui.posts.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.twcgilbert.postsapp.common.ui.adapter.AdapterItemClick
import com.example.twcgilbert.postsapp.databinding.PostItemBinding
import com.example.twcgilbert.postsapp.repo.model.Post
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityContract

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostsAdapter(
        private val onPostClicked: PostsActivityContract.PostClicked?) :
        ListAdapter<Post, PostItemViewHolder>(object : DiffUtil.ItemCallback<Post>() {

            override fun areItemsTheSame(oldItem: Post?, newItem: Post?) =
                    oldItem?.id == newItem?.id

            override fun areContentsTheSame(oldItem: Post?, newItem: Post?) =
                    oldItem == newItem
        }),
        AdapterItemClick {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val binding = PostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return PostItemViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bindAndExecutePending(getItem(position));
    }

    override fun onItemClick(position: Int) {
        onPostClicked?.onPostClicked(getItem(position))
    }
}