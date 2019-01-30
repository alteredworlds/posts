package com.example.twcgilbert.postsapp.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twcgilbert.postsapp.common.di.ViewModelFactory
import com.example.twcgilbert.postsapp.common.di.ViewModelKey
import com.example.twcgilbert.postsapp.ui.detail.PostDetailActivityViewModel
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostsActivityViewModel::class)
    internal abstract fun bindPostsActivityViewModel(viewModel: PostsActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailActivityViewModel::class)
    internal abstract fun bindsPostDetailActivityViewModel(viewModel: PostDetailActivityViewModel): ViewModel
}