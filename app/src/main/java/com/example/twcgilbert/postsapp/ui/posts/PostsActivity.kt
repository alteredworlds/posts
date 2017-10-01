package com.example.twcgilbert.postsapp.ui.posts

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.twcgilbert.postsapp.R
import com.example.twcgilbert.postsapp.common.ui.BaseActivity
import com.example.twcgilbert.postsapp.databinding.PostsActivityBinding
import com.example.twcgilbert.postsapp.io.data.Post
import com.example.twcgilbert.postsapp.io.data.putPost
import com.example.twcgilbert.postsapp.ui.detail.PostDetailActivity
import timber.log.Timber
import javax.inject.Inject

class PostsActivity : BaseActivity(), PostsActivityContract.View {

    @Inject lateinit var viewModel: PostsActivityContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<PostsActivityBinding>(
                this,
                R.layout.posts_activity)
        binding.viewModel = viewModel
        viewModel.onCreate()
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }

    override fun navigateForPost(post: Post) {
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.putPost(post)
        startActivity(intent)
    }
}
