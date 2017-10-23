package com.example.twcgilbert.postsapp.ui.detail

import com.example.twcgilbert.postsapp.io.Constants
import com.example.twcgilbert.postsapp.io.DataRepositoryFakeImpl
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
class PostDetailActivityViewModelTests : PostTestBase() {

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var view: PostDetailActivityContract.View

    private lateinit var viewModel: PostDetailActivityViewModel

    override fun cleanup() {
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
                DataRepositoryFakeImpl.companion.testPost1,
                DataRepositoryFakeImpl())
        assertEquals(DataRepositoryFakeImpl.companion.post1Title, viewModel.postTitle.get())
        assertEquals(DataRepositoryFakeImpl.companion.post1Body, viewModel.postBody.get())
        assertEquals(DataRepositoryFakeImpl.companion.userId1Username, viewModel.postUserName.get())

        // Constants.ADORABLE_URL + userEmail + Constants.IMAGE_EXTENSION
        assertEquals(
                Constants.ADORABLE_URL + DataRepositoryFakeImpl.companion.userId1Email + Constants.IMAGE_EXTENSION,
                viewModel.userAvatarUrl.get())
        assertEquals(0, viewModel.postNumberOfComments.get())
    }

    fun runViewModel2Tests() {
        viewModel = PostDetailActivityViewModel(
                view,
                DataRepositoryFakeImpl.companion.testPost2,
                DataRepositoryFakeImpl())
        assertEquals(DataRepositoryFakeImpl.companion.post2Title, viewModel.postTitle.get())
        assertEquals(DataRepositoryFakeImpl.companion.post2Body, viewModel.postBody.get())
        assertEquals(DataRepositoryFakeImpl.companion.userId2Username, viewModel.postUserName.get())

        // Constants.ADORABLE_URL + userEmail + Constants.IMAGE_EXTENSION
        assertEquals(
                Constants.ADORABLE_URL + DataRepositoryFakeImpl.companion.userId2Email + Constants.IMAGE_EXTENSION,
                viewModel.userAvatarUrl.get())
        assertEquals(0, viewModel.postNumberOfComments.get())
    }
}