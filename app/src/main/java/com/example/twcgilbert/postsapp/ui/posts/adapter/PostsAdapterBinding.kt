package com.example.twcgilbert.postsapp.ui.posts.adapter

import android.arch.paging.PagedList
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.example.twcgilbert.postsapp.repo.model.Post
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityContract

/**
 * Created by twcgilbert on 02/10/2017.
 */

@BindingAdapter("posts", "postsClickListener", requireAll = true)
fun bindPostsAdapter(recyclerView: RecyclerView,
                     posts: PagedList<Post>?,
                     onPostClicked: PostsActivityContract.PostClicked?) {
    var postsAdapter = recyclerView.adapter as? PostsAdapter
    if (null == postsAdapter) {
        postsAdapter = PostsAdapter(onPostClicked)
        recyclerView.adapter = postsAdapter
    }
    postsAdapter.submitList(posts)
}