package com.example.twcgilbert.postsapp.ui.detail

import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.data.Post
import com.example.twcgilbert.postsapp.repo.data.imageUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostDetailActivityViewModel(
        private val view: PostDetailActivityContract.View,
        private val post: Post,
        private val repository: DataRepository) :
        PostDetailActivityContract.ViewModel {

    private val disposables = CompositeDisposable()

    override val postTitle = ObservableField<String>(post.title)

    override val postBody = ObservableField<String>(post.body)

    override val postUserName = ObservableField<String>(post.userName)

    override val userAvatarUrl =  ObservableField<String>(post.imageUrl)

    override val postNumberOfComments = ObservableInt()

    override fun onCreate() {
        // find number of comments
        disposables.add(repository
                .getComments(post.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            postNumberOfComments.set(it.size)
                        },
                        {
                            view.showError(it.localizedMessage)
                        }
                )
        )
    }

    override fun onDestroy() {
        disposables.dispose()
    }
}