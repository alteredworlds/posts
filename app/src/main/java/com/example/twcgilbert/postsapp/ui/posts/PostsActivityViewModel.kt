package com.example.twcgilbert.postsapp.ui.posts

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.data.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostsActivityViewModel(
        private val view: PostsActivityContract.View,
        private val repository: DataRepository) :
        PostsActivityContract.ViewModel {

    private val disposables = CompositeDisposable()

    override val posts = ObservableField<List<Post>>(ArrayList<Post>(0))

    override val progressVisible = ObservableBoolean()

    override fun onCreate() {
        progressVisible.set(true)
        disposables.add(repository.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Timber.d("We should now have %d posts!", it.size);
                            progressVisible.set(false)
                            posts.set(it)
                        },
                        {
                            Timber.d(it, "Failed to retrieve posts!")
                            progressVisible.set(false)
                            view.showError(it.localizedMessage)
                        }))
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    override fun onPostClicked(post: Post) {
        Timber.d("Clicked Post %s", post);
        view.navigateForPost(post)
    }
}