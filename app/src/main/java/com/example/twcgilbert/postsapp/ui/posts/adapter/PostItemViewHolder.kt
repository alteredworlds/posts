package com.example.twcgilbert.postsapp.ui.posts.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.twcgilbert.postsapp.BR
import com.example.twcgilbert.postsapp.common.ui.adapter.AdapterItemClick
import com.example.twcgilbert.postsapp.repo.model.Post
import com.example.twcgilbert.postsapp.ui.avatar.imageUrl

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostItemViewHolder(
        private val binding: ViewDataBinding,
        private val itemClick: AdapterItemClick?) :
        RecyclerView.ViewHolder(binding.root),
        PostItemContract {

    override var title: String? = null

    override var userAvatarUrl: String? = null

    override fun onClick(p0: View?) {
        itemClick?.onItemClick(adapterPosition)
    }

    fun bindAndExecutePending(postItem: Post?) {
        // transfer the data
        update(postItem)
        // bind to this instance (acting as ViewModel for the list item)
        binding.setVariable(BR.viewModel, this)
        // ensure updated ASAP
        binding.executePendingBindings()
    }

    private fun update(postItem: Post?) {
        title = postItem?.title
        userAvatarUrl = postItem?.imageUrl
    }
}