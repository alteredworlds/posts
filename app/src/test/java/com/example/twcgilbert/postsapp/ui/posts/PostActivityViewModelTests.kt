package com.example.twcgilbert.postsapp.ui.posts

import com.example.twcgilbert.postsapp.io.DataRepositoryImpl
import com.example.twcgilbert.postsapp.io.PostsServiceEmpty
import com.example.twcgilbert.postsapp.io.PostsServiceFake
import com.example.twcgilbert.postsapp.io.PostsServiceFakeDelayed
import com.example.twcgilbert.postsapp.io.PostsServiceFakeDelayed.companion.postsDelay
import com.example.twcgilbert.postsapp.io.PostsServiceFakeDelayed.companion.usersDelay
import com.example.twcgilbert.postsapp.ui.PostTestBase
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

/**
 * Created by twcgilbert on 23/10/2017.
 */
class PostActivityViewModelTests : PostTestBase() {

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var view: PostsActivityContract.View

    private lateinit var viewModel: PostsActivityViewModel

    override fun cleanup() {
        viewModel.onDestroy()
    }

    @Test
    fun testViewModel() {

        viewModel = PostsActivityViewModel(
                view,
                DataRepositoryImpl(PostsServiceEmpty()))

        expectEmptyAndNotInProgress()
    }

    @Test
    fun testViewModelRetrieveData() {

        viewModel = PostsActivityViewModel(
                view,
                DataRepositoryImpl(PostsServiceFake()))

        expectEmptyAndNotInProgress()

        testScheduler.advanceTimeTo(2, TimeUnit.SECONDS)

        expectEmptyAndNotInProgress()

        // we've now called subscribe(...)
        viewModel.onCreate()

        // in progress but still no items, as time hasn't advanced since subscribe(...)
        expectEmptyAndInProgress()

        // OK, advance time by tiny increment...
        testScheduler.advanceTimeBy(1, TimeUnit.MICROSECONDS)

        // with no delay, we now expect results
        expectFullAndNotInProgress()
    }

    @Test
    fun testViewModelRetrieveDataDelayed() {

        viewModel = PostsActivityViewModel(
                view,
                DataRepositoryImpl(PostsServiceFakeDelayed()))

        expectEmptyAndNotInProgress()

        // we expect zero results, no progress after moving time forward
        testScheduler.advanceTimeTo(5, TimeUnit.HOURS)
        expectEmptyAndNotInProgress()

        // reset the timeline
        testScheduler.advanceTimeTo(0, TimeUnit.SECONDS)

        // we've now called subscribe(...) so now 'in progress'
        viewModel.onCreate()

        // but still expect no items, as time hasn't advanced since subscribe(...)
        expectEmptyAndInProgress()

        // OK, advance time to 1 second
        testScheduler.advanceTimeTo(1, TimeUnit.SECONDS)
        expectEmptyAndInProgress()

        // OK, advance time to 2 seconds
        testScheduler.advanceTimeTo(2, TimeUnit.SECONDS)
        expectEmptyAndInProgress()

        // now to 3 seconds
        testScheduler.advanceTimeTo(Math.max(postsDelay, usersDelay),
                TimeUnit.SECONDS)
        expectFullAndNotInProgress()
    }

    fun expectEmptyAndNotInProgress() {
        assertEquals(0, viewModel.posts.get()?.size)
        assertEquals(false, viewModel.progressVisible.get())
    }

    fun expectEmptyAndInProgress() {
        assertEquals(0, viewModel.posts.get()?.size)
        assertEquals(true, viewModel.progressVisible.get())
    }

    fun expectFullAndNotInProgress() {
        assertEquals(16, viewModel.posts.get()?.size)
        assertEquals(false, viewModel.progressVisible.get())
    }
}