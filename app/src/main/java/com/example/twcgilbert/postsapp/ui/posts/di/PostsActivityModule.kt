package com.example.twcgilbert.postsapp.ui.posts.di

import com.example.twcgilbert.postsapp.common.di.ActivityScope
import com.example.twcgilbert.postsapp.io.DataRepository
import com.example.twcgilbert.postsapp.ui.posts.PostsActivity
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityContract
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by twcgilbert on 01/10/2017.
 */
@Module
class PostsActivityModule {

    @Provides
    @ActivityScope
    fun providesView(activity: PostsActivity): PostsActivityContract.View = activity

    @Provides
    @ActivityScope
    fun providesViewModel(
            view: PostsActivityContract.View,
            repository: DataRepository): PostsActivityContract.ViewModel =
            PostsActivityViewModel(view, repository)
}