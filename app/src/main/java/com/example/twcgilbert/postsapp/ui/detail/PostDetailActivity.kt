package com.example.twcgilbert.postsapp.ui.detail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.twcgilbert.postsapp.R
import com.example.twcgilbert.postsapp.common.ui.BaseActivity
import com.example.twcgilbert.postsapp.databinding.PostDetailActivityBinding
import com.example.twcgilbert.postsapp.repo.model.getPost
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<PostDetailActivityBinding>(
                this,
                R.layout.post_detail_activity)
        binding.setLifecycleOwner(this)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PostDetailActivityViewModel::class.java)
        viewModel.post = intent.getPost()
        binding.viewModel = viewModel
    }
}