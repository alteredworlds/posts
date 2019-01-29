package com.example.twcgilbert.postsapp.ui.detail

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.domain.RefreshCommentsUseCase
import com.example.twcgilbert.postsapp.repo.model.Post
import com.example.twcgilbert.postsapp.ui.avatar.imageUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostDetailActivityViewModel(
        private val post: Post,
        private val repository: DataRepository,
        private val refreshUseCase: RefreshCommentsUseCase) :
        PostDetailActivityContract.ViewModel {

    private val disposables = CompositeDisposable()

    override val error = ObservableField<String>()

    override val postTitle = ObservableField<String>(post.title)

    override val postBody = ObservableField<String>(post.body)

    override val postUserName = ObservableField<String>(post.userName)

    override val userAvatarUrl =  ObservableField<String>(post.imageUrl)

    override val postNumberOfComments = ObservableInt()

    override fun onCreate() {
        // find number of comments from cache
        disposables.add(repository
                .getNumComments(post.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            postNumberOfComments.set(it)
                            error.set("")
                        },
                        {
                            error.set(it.localizedMessage)
                        }
                )
        )
        // also initiate network refresh
        disposables.add(refreshUseCase
                .execute(post.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Timber.d("Retrieved comments for ${post.id}")
                            error.set("")
                        },
                        {
                            error.set(it.localizedMessage)
                        }
                ))
    }

    override fun onDestroy() {
        disposables.dispose()
    }
}