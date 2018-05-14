package com.example.twcgilbert.postsapp.ui.detail.di

import com.example.twcgilbert.postsapp.common.di.ActivityScope
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.domain.RefreshCommentsUseCase
import com.example.twcgilbert.postsapp.repo.model.Post
import com.example.twcgilbert.postsapp.repo.model.getPost
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
    @ActivityScope
    fun providesPost(activity: PostDetailActivity): Post = activity.intent.getPost()

    @Provides
    @ActivityScope
    fun providesViewModel(
            post: Post,
            repository: DataRepository,
            refreshUseCase: RefreshCommentsUseCase): PostDetailActivityContract.ViewModel =
            PostDetailActivityViewModel(post, repository, refreshUseCase)
}