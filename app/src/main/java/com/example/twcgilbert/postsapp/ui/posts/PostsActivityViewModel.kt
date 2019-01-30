package com.example.twcgilbert.postsapp.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.twcgilbert.postsapp.common.ui.util.Event
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.domain.RefreshPostsUseCase
import com.example.twcgilbert.postsapp.repo.model.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by twcgilbert on 01/10/2017.
 */
class PostsActivityViewModel @Inject constructor(
        repository: DataRepository,
        private val refreshUseCase: RefreshPostsUseCase) :
        ViewModel(), PostsActivityContract.ViewModel {

    companion object {
        val PAGE_SIZE = 20
        val PAGE_HINT_INITIAL_LOAD = 60  // default is 3 * PAGE_SIZE
        val PAGE_PREFETCH_DISTANCE = 20 // default is PAGE_SIZE
    }

    private val disposables = CompositeDisposable()

    override val error = MutableLiveData<String>()

    override val posts = MutableLiveData<PagedList<Post>>()

    override val progressVisible = MutableLiveData<Boolean>()

    override val navigateForPost = MutableLiveData<Event<Post>>()

    init {
        error.postValue("")
        disposables.add(
                RxPagedListBuilder(
                        repository.getPosts(),
                        PagedList.Config.Builder()
                                .setPageSize(PAGE_SIZE)
                                .setInitialLoadSizeHint(PAGE_HINT_INITIAL_LOAD)
                                .setPrefetchDistance(PAGE_PREFETCH_DISTANCE)
                                .setEnablePlaceholders(true)
                                .build())
                        .buildObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    Timber.d("We should now have %d posts!", it.size);
                                    posts.postValue(it)
                                },
                                {
                                    Timber.d(it, "Failed to retrieve posts!")
                                    error.postValue(it.localizedMessage)
                                }))
        // we should also refresh
        onRefresh()
    }

    override fun onRefresh() {
        progressVisible.postValue(true)
        error.postValue("")
        disposables.add(refreshUseCase.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            progressVisible.postValue(false)
                            Timber.d("Data retrieved!");
                        },
                        {
                            Timber.d(it, "Failed to retrieve posts!")
                            progressVisible.postValue(false)
                            error.postValue(it.localizedMessage)
                        }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    override fun onPostClicked(post: Post) {
        Timber.d("Clicked Post %s", post);
        navigateForPost.postValue(Event(post))
    }
}