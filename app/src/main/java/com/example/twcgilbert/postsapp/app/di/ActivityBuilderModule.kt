package com.example.twcgilbert.postsapp.app.di

import com.example.twcgilbert.postsapp.common.di.ActivityScope
import com.example.twcgilbert.postsapp.ui.detail.PostDetailActivity
import com.example.twcgilbert.postsapp.ui.detail.di.PostDetailActivityModule
import com.example.twcgilbert.postsapp.ui.posts.PostsActivity
import com.example.twcgilbert.postsapp.ui.posts.di.PostsActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by twcgilbert on 01/10/2017.
 */
@Module
abstract class ActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(PostsActivityModule::class))
    abstract fun bindPostsActivity(): PostsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(PostDetailActivityModule::class))
    abstract fun bindPostDetailActivity(): PostDetailActivity
}