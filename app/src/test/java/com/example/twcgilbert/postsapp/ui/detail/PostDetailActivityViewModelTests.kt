package com.example.twcgilbert.postsapp.ui.posts

import com.example.twcgilbert.postsapp.io.Constants
import com.example.twcgilbert.postsapp.io.DataRepositoryFakeImpl
import com.example.twcgilbert.postsapp.io.DataRepositoryFakeImpl.companion
import com.example.twcgilbert.postsapp.ui.detail.PostDetailActivityContract
import com.example.twcgilbert.postsapp.ui.detail.PostDetailActivityViewModel
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals


/**
 * Created by twcgilbert on 02/10/2017.
 */
class PostDetailActivityViewModelTests {

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var view: PostDetailActivityContract.View

    private lateinit var viewModel: PostDetailActivityViewModel

    private val testScheduler = TestScheduler()

    @After
    fun cleanup() {
        viewModel.onDestroy()
    }

    @Test
    fun testViewModel() {
        runViewModel1Tests()
    }

    @Test
    fun testViewModelNonZeroNumberOfComments() {
        runViewModel1Tests()

        // subscribe to comments
        viewModel.onCreate()

        // we still expect 0 comments though, since scheduler hasn't advanced
        assertEquals(0, viewModel.postNumberOfComments.get())

        // now advance the scheduler
        testScheduler.advanceTimeBy(2, TimeUnit.SECONDS)

        // we should expect one comment now
        assertEquals(1, viewModel.postNumberOfComments.get())
    }

    @Test
    fun testViewModelNoComments() {
        runViewModel2Tests()

        // subscribe to comments
        viewModel.onCreate()

        // we still expect 0 comments though, since scheduler hasn't advanced
        assertEquals(0, viewModel.postNumberOfComments.get())

        // now advance the scheduler
        testScheduler.advanceTimeBy(2, TimeUnit.SECONDS)

        // we should still expect 0 comments
        assertEquals(0, viewModel.postNumberOfComments.get())
    }

    fun runViewModel1Tests() {
        viewModel = PostDetailActivityViewModel(
                view,
                companion.testPost1,
                DataRepositoryFakeImpl(),
                testScheduler,
                testScheduler)
        assertEquals(companion.post1Title, viewModel.postTitle.get())
        assertEquals(companion.post1Body, viewModel.postBody.get())
        assertEquals(companion.userId1Username, viewModel.postUserName.get())

        // Constants.ADORABLE_URL + userEmail + Constants.IMAGE_EXTENSION
        assertEquals(
                Constants.ADORABLE_URL + companion.userId1Email + Constants.IMAGE_EXTENSION,
                viewModel.userAvatarUrl.get())
        assertEquals(0, viewModel.postNumberOfComments.get())
    }

    fun runViewModel2Tests() {
        viewModel = PostDetailActivityViewModel(
                view,
                companion.testPost2,
                DataRepositoryFakeImpl(),
                testScheduler,
                testScheduler)
        assertEquals(companion.post2Title, viewModel.postTitle.get())
        assertEquals(companion.post2Body, viewModel.postBody.get())
        assertEquals(companion.userId2Username, viewModel.postUserName.get())

        // Constants.ADORABLE_URL + userEmail + Constants.IMAGE_EXTENSION
        assertEquals(
                Constants.ADORABLE_URL + companion.userId2Email + Constants.IMAGE_EXTENSION,
                viewModel.userAvatarUrl.get())
        assertEquals(0, viewModel.postNumberOfComments.get())
    }
}