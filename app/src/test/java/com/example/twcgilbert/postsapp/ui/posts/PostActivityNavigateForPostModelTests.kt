package com.example.twcgilbert.postsapp.ui.posts

import com.example.twcgilbert.postsapp.repo.DataRepositoryImpl
import com.example.twcgilbert.postsapp.repo.domain.RefreshPostsUseCase
import com.example.twcgilbert.postsapp.repo.persistence.LocalDatabase
import com.example.twcgilbert.postsapp.ui.PostTestBase
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

/**
 * Created by twcgilbert on 23/10/2017.
 */
class PostActivityNavigateForPostModelTests : PostTestBase() {

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var navigateForPost: PostsActivityContract.NavigateForPost

    @Mock
    private lateinit var db: LocalDatabase

    @Mock
    private lateinit var postsUseCase: RefreshPostsUseCase

    private lateinit var viewModel: PostsActivityViewModel

    override fun cleanup() {
        viewModel.onDestroy()
    }

    @Test
    fun testViewModel() {

        viewModel = PostsActivityViewModel(
                navigateForPost,
                DataRepositoryImpl(db),
                postsUseCase)

        expectEmptyAndNotInProgress()
    }

    //
//    @Test
//    fun testViewModelRetrieveData() {
//
//        viewModel = PostsActivityViewModel(
//                navigateForPost,
//                DataRepositoryImpl(PostsServiceFake()))
//
//        expectEmptyAndNotInProgress()
//
//        testScheduler.advanceTimeTo(2, TimeUnit.SECONDS)
//
//        expectEmptyAndNotInProgress()
//
//        // we've now called subscribe(...)
//        viewModel.onCreate()
//
//        // in progress but still no items, as time hasn't advanced since subscribe(...)
//        expectEmptyAndInProgress()
//
//        // OK, advance time by tiny increment...
//        testScheduler.advanceTimeBy(1, TimeUnit.MICROSECONDS)
//
//        // with no delay, we now expect results
//        expectFullAndNotInProgress()
//    }
//
//    @Test
//    fun testViewModelRetrieveDataDelayed() {
//
//        viewModel = PostsActivityViewModel(
//                navigateForPost,
//                DataRepositoryImpl(PostsServiceFakeDelayed()))
//
//        expectEmptyAndNotInProgress()
//
//        // we expect zero results, no progress after moving time forward
//        testScheduler.advanceTimeTo(5, TimeUnit.HOURS)
//        expectEmptyAndNotInProgress()
//
//        // reset the timeline
//        testScheduler.advanceTimeTo(0, TimeUnit.SECONDS)
//
//        // we've now called subscribe(...) so now 'in progress'
//        viewModel.onCreate()
//
//        // but still expect no items, as time hasn't advanced since subscribe(...)
//        expectEmptyAndInProgress()
//
//        // OK, advance time to 1 second
//        testScheduler.advanceTimeTo(1, TimeUnit.SECONDS)
//        expectEmptyAndInProgress()
//
//        // OK, advance time to 2 seconds
//        testScheduler.advanceTimeTo(2, TimeUnit.SECONDS)
//        expectEmptyAndInProgress()
//
//        // now to 3 seconds
//        testScheduler.advanceTimeTo(
//                Math.max(PostsServiceFakeDelayed.postsDelay, PostsServiceFakeDelayed.usersDelay),
//                TimeUnit.SECONDS)
//        expectFullAndNotInProgress()
//    }
//
    fun expectEmptyAndNotInProgress() {
        assertEquals(null, viewModel.posts.get()?.size)
        assertEquals(false, viewModel.progressVisible.get())
    }
//
//    fun expectEmptyAndInProgress() {
//        assertEquals(0, viewModel.posts.get()?.size)
//        assertEquals(true, viewModel.progressVisible.get())
//    }
//
//    fun expectFullAndNotInProgress() {
//        assertEquals(16, viewModel.posts.get()?.size)
//        assertEquals(false, viewModel.progressVisible.get())
//    }
}