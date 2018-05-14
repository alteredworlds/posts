package com.example.twcgilbert.postsapp.ui.posts.di

import com.example.twcgilbert.postsapp.common.di.ActivityScope
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.domain.RefreshPostsUseCase
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
    fun providesNavigateForPost(activity: PostsActivity): PostsActivityContract.NavigateForPost = activity

    @Provides
    @ActivityScope
    fun providesViewModel(
            navigateForPost: PostsActivityContract.NavigateForPost,
            repository: DataRepository,
            refreshUseCase: RefreshPostsUseCase): PostsActivityContract.ViewModel =
            PostsActivityViewModel(
                    navigateForPost,
                    repository,
                    refreshUseCase)
}