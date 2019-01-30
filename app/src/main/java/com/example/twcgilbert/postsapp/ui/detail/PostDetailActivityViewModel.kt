package com.example.twcgilbert.postsapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.domain.RefreshCommentsUseCase
import com.example.twcgilbert.postsapp.repo.model.Post
import com.example.twcgilbert.postsapp.ui.avatar.imageUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostDetailActivityViewModel @Inject constructor(
        private val repository: DataRepository,
        private val refreshUseCase: RefreshCommentsUseCase) :
        ViewModel(), PostDetailActivityContract.ViewModel {

    private val disposables = CompositeDisposable()

    override val postTitle = MutableLiveData<String>()

    override val postBody = MutableLiveData<String>()

    override val postUserName = MutableLiveData<String>()

    override val userAvatarUrl = MutableLiveData<String>()

    override val error = MutableLiveData<String>()

    override val postNumberOfComments = MutableLiveData<Int>()

    var post: Post? = null
        set(value) {
            if (field != value) {
                field = value
                updatePostInfo(value)
            }
        }

    private fun updatePostInfo(post: Post?) {
        postTitle.postValue(post?.title)
        postBody.postValue(post?.body)
        postUserName.postValue(post?.userName)
        userAvatarUrl.postValue(post?.imageUrl)

        if (null != post) {
            requestNumberOfComments(post.id)
        }
    }

    private fun requestNumberOfComments(postId: Int) {
        // find number of comments from cache
        disposables.add(repository
                .getNumComments(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            postNumberOfComments.value = it
                            error.value = ""
                        },
                        {
                            error.value = it.localizedMessage
                        }
                )
        )
        // also initiate network refresh
        disposables.add(refreshUseCase
                .execute(postId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Timber.d("Retrieved comments for ${postId}")
                            error.value = ""
                        },
                        {
                            error.value = it.localizedMessage
                        }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}