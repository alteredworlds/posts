package com.example.twcgilbert.postsapp.ui.detail

import com.example.twcgilbert.postsapp.repo.Constants
import com.example.twcgilbert.postsapp.repo.DataRepositoryImpl
import com.example.twcgilbert.postsapp.repo.PostsServiceFake
import com.example.twcgilbert.postsapp.repo.PostsServiceFakeDelayed
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
    fun testViewModelNonZeroNumberOfCommentsDelayed() {
        viewModel = PostDetailActivityViewModel(
                view,
                PostsServiceFake.testPost1,
                DataRepositoryImpl(PostsServiceFakeDelayed()))

        // subscribe to comments
        viewModel.onCreate()

        // we still expect 0 comments though, since scheduler hasn't advanced
        assertEquals(0, viewModel.postNumberOfComments.get())

        // now advance the scheduler
        testScheduler.advanceTimeBy(2, TimeUnit.SECONDS)

        // we still expect 0 comments though, since scheduler hasn't advanced ENOUGH
        assertEquals(0, viewModel.postNumberOfComments.get())

        // now advance the scheduler
        testScheduler.advanceTimeTo(PostsServiceFakeDelayed.commentsDelay, TimeUnit.SECONDS)

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
                PostsServiceFake.testPost1,
                DataRepositoryImpl(PostsServiceFake()))
        assertEquals(PostsServiceFake.post1Title, viewModel.postTitle.get())
        assertEquals(PostsServiceFake.post1Body, viewModel.postBody.get())
        assertEquals(PostsServiceFake.userId1Username, viewModel.postUserName.get())

        // Constants.ADORABLE_URL + userEmail + Constants.IMAGE_EXTENSION
        assertEquals(
                Constants.ADORABLE_URL + PostsServiceFake.userId1Email + Constants.IMAGE_EXTENSION,
                viewModel.userAvatarUrl.get())
        assertEquals(0, viewModel.postNumberOfComments.get())
    }

    fun runViewModel2Tests() {
        viewModel = PostDetailActivityViewModel(
                view,
                PostsServiceFake.testPost2,
                DataRepositoryImpl(PostsServiceFake()))
        assertEquals(PostsServiceFake.post2Title, viewModel.postTitle.get())
        assertEquals(PostsServiceFake.post2Body, viewModel.postBody.get())
        assertEquals(PostsServiceFake.userId2Username, viewModel.postUserName.get())

        // Constants.ADORABLE_URL + userEmail + Constants.IMAGE_EXTENSION
        assertEquals(
                Constants.ADORABLE_URL + PostsServiceFake.userId2Email + Constants.IMAGE_EXTENSION,
                viewModel.userAvatarUrl.get())
        assertEquals(0, viewModel.postNumberOfComments.get())
    }
}