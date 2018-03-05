package com.example.twcgilbert.postsapp.ui.posts

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.twcgilbert.postsapp.io.DataRepository
import com.example.twcgilbert.postsapp.io.data.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

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

        val myRandomObservable = Observable.just("")
                .subscribeOn(Schedulers.computation())
                .doOnNext { Timber.d("[1] Which thread? -> %s", Thread.currentThread().name) }
                .doOnNext { Timber.d("[2] Which thread? -> %s", Thread.currentThread().name) }
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .doOnNext { Timber.d("[3] Which thread? -> %s", Thread.currentThread().name) }
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400L, TimeUnit.MILLISECONDS)
                .subscribe({ Timber.d("[4] Which thread? -> %s", Thread.currentThread().name) })
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    override fun onPostClicked(post: Post) {
        Timber.d("Clicked Post %s", post);
        view.navigateForPost(post)
    }
}