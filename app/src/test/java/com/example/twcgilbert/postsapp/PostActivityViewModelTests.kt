package com.example.twcgilbert.postsapp

import com.example.twcgilbert.postsapp.io.DataRepositoryFakeImpl
import com.example.twcgilbert.postsapp.io.EmptyDataRepositoryImpl
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityContract
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityViewModel
import io.reactivex.schedulers.TestScheduler
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals


/**
 * Created by twcgilbert on 02/10/2017.
 */
class PostActivityViewModelTests {

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var view: PostsActivityContract.View

    private lateinit var viewModel: PostsActivityViewModel

    private val testScheduler = TestScheduler()

    @Test
    fun testViewModel() {

        viewModel = PostsActivityViewModel(
                view,
                EmptyDataRepositoryImpl(),
                testScheduler,
                testScheduler)
        assertEquals(0, viewModel.posts.get().size)
        assertEquals(false, viewModel.progressVisible.get())
    }

    @Test
    fun testViewModelRetrieveData() {

        viewModel = PostsActivityViewModel(
                view,
                DataRepositoryFakeImpl(),
                testScheduler,
                testScheduler)

        assertEquals(0, viewModel.posts.get().size)
        assertEquals(false, viewModel.progressVisible.get())

        testScheduler.advanceTimeBy(2, TimeUnit.SECONDS)

        // we still expect zero results, no progress
        assertEquals(0, viewModel.posts.get().size)
        assertEquals(false, viewModel.progressVisible.get())

        // we've now called subscribe(...)
        viewModel.onCreate()

        // but still expect no items, as time hasn't advanced since subscribe(...)
        assertEquals(0, viewModel.posts.get().size)
        // but progressVisible should now be true
        assertEquals(true, viewModel.progressVisible.get())

        // OK, advance time
        testScheduler.advanceTimeBy(2, TimeUnit.SECONDS)

        // we now expect to have the full test result set
        assertEquals(16, viewModel.posts.get().size)
        // progressVisible should now be false
        assertEquals(false, viewModel.progressVisible.get())

        // we may as well clean up the subscriptions
        viewModel.onDestroy()
    }
}