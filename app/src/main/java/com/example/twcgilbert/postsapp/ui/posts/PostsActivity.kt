package com.example.twcgilbert.postsapp.ui.posts

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.twcgilbert.postsapp.R
import com.example.twcgilbert.postsapp.common.ui.BaseActivity
import com.example.twcgilbert.postsapp.common.ui.util.observeEvent
import com.example.twcgilbert.postsapp.databinding.PostsActivityBinding
import com.example.twcgilbert.postsapp.repo.model.Post
import com.example.twcgilbert.postsapp.repo.model.putPost
import com.example.twcgilbert.postsapp.ui.detail.PostDetailActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class PostsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PostsActivityContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<PostsActivityBinding>(
                this,
                R.layout.posts_activity)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PostsActivityViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        viewModel.navigateForPost.observeEvent(this, ::navigateForPost)
    }

    private fun navigateForPost(post: Post) {
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.putPost(post)
        startActivity(intent)
    }
}
