package com.example.twcgilbert.postsapp.ui.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.twcgilbert.postsapp.common.ui.adapter.AdapterItemClick
import com.example.twcgilbert.postsapp.databinding.PostItemBinding
import com.example.twcgilbert.postsapp.repo.model.Post
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityContract

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostsAdapter(private val onPostClicked: PostsActivityContract.PostClicked?) :
        PagedListAdapter<Post, PostItemViewHolder>(DiffUtilItemCallbackPost()),
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
        val item = getItem(position)
        if (null != item) {
            onPostClicked?.onPostClicked(item)
        }
    }

    override fun onViewAttachedToWindow(holder: PostItemViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAppear()
    }

    override fun onViewDetachedFromWindow(holder: PostItemViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDisappear()
    }

    private class DiffUtilItemCallbackPost : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
                oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
                oldItem == newItem
    }
}