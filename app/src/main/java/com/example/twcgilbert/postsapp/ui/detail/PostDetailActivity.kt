package com.example.twcgilbert.postsapp.ui.detail

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.twcgilbert.postsapp.R
import com.example.twcgilbert.postsapp.common.ui.BaseActivity
import com.example.twcgilbert.postsapp.databinding.PostDetailActivityBinding
import javax.inject.Inject

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostDetailActivity : BaseActivity(), PostDetailActivityContract.View {

    @Inject lateinit var viewModel: PostDetailActivityContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<PostDetailActivityBinding>(
                this,
                R.layout.post_detail_activity)
        binding.viewModel = viewModel
        viewModel.onCreate()
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}