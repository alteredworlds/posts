package com.example.twcgilbert.postsapp.ui.posts

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.domain.RefreshPostsUseCase
import com.example.twcgilbert.postsapp.repo.model.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.ref.WeakReference

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostsActivityViewModel(
        navigateForPost: PostsActivityContract.NavigateForPost,
        private val repository: DataRepository,
        private val refreshUseCase: RefreshPostsUseCase) :
        PostsActivityContract.ViewModel {

    private val navigator = WeakReference<PostsActivityContract.NavigateForPost>(navigateForPost)

    private val disposables = CompositeDisposable()

    override val error = ObservableField<String>()

    override val posts = ObservableField<List<Post>>(ArrayList<Post>(0))

    override val progressVisible = ObservableBoolean()

    override fun onCreate() {
        error.set("")
        disposables.add(repository.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Timber.d("We should now have %d posts!", it.size);
                            posts.set(it)
                        },
                        {
                            Timber.d(it, "Failed to retrieve posts!")
                            error.set(it.localizedMessage)
                        }))
        // we should also refresh
        onRefresh()
    }

    override fun onRefresh() {
        progressVisible.set(true)
        error.set("")
        disposables.add(refreshUseCase.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            progressVisible.set(false)
                            Timber.d("Data retrieved!");
                        },
                        {
                            Timber.d(it, "Failed to retrieve posts!")
                            progressVisible.set(false)
                            error.set(it.localizedMessage)
                        }
                ))
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    override fun onPostClicked(post: Post) {
        Timber.d("Clicked Post %s", post);
        navigator.get()?.navigateForPost(post)
    }
}