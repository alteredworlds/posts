package com.example.twcgilbert.postsapp.ui.posts.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.twcgilbert.postsapp.common.ui.AdapterItemClick
import com.example.twcgilbert.postsapp.databinding.PostItemBinding
import com.example.twcgilbert.postsapp.repo.data.Post
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityContract

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostsAdapter(private val onPostClicked: PostsActivityContract.PostClicked?) :
        RecyclerView.Adapter<PostItemViewHolder>(),
        AdapterItemClick {

    var items = ArrayList<Post>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val binding = PostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return PostItemViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bindAndExecutePending(items[position]);
    }

    override fun onItemClick(position: Int) {
        onPostClicked?.onPostClicked(items[position])
    }

    fun setItems(newItems: List<Post>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}