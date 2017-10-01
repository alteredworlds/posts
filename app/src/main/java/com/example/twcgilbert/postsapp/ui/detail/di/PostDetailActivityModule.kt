package com.example.twcgilbert.postsapp.ui.detail.di

import com.example.twcgilbert.postsapp.io.DataRepository
import com.example.twcgilbert.postsapp.io.data.Post
import com.example.twcgilbert.postsapp.io.data.getPost
import com.example.twcgilbert.postsapp.ui.detail.PostDetailActivity
import com.example.twcgilbert.postsapp.ui.detail.PostDetailActivityContract
import com.example.twcgilbert.postsapp.ui.detail.PostDetailActivityViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by twcgilbert on 01/10/2017.
 */
@Module
class PostDetailActivityModule {

    @Provides
    fun providesPost(activity: PostDetailActivity): Post = activity.intent.getPost()

    @Provides
    fun providesView(activity: PostDetailActivity): PostDetailActivityContract.View = activity

    @Provides
    fun providesViewModel(
            view: PostDetailActivityContract.View,
            post: Post,
            repository: DataRepository):
            PostDetailActivityContract.ViewModel = PostDetailActivityViewModel(view, post, repository)
}