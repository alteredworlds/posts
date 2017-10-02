package com.example.twcgilbert.postsapp.ui.posts.di

import com.example.twcgilbert.postsapp.io.DataRepository
import com.example.twcgilbert.postsapp.ui.posts.PostsActivity
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityContract
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by twcgilbert on 01/10/2017.
 */
@Module
class PostsActivityModule {

    @Provides
    fun providesView(activity: PostsActivity): PostsActivityContract.View = activity

    @Provides
    fun providesViewModel(
            view: PostsActivityContract.View,
            repository: DataRepository): PostsActivityContract.ViewModel =
            PostsActivityViewModel(view,
                    repository,
                    Schedulers.io(),
                    AndroidSchedulers.mainThread())
}