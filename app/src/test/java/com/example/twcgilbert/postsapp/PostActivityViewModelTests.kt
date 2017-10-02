package com.example.twcgilbert.postsapp

import com.example.twcgilbert.postsapp.io.EmptyDataRepositoryImpl
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityContract
import com.example.twcgilbert.postsapp.ui.posts.PostsActivityViewModel
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

/**
 * Created by twcgilbert on 02/10/2017.
 */
class PostActivityViewModelTests {

    @Mock private lateinit var view: PostsActivityContract.View

    private lateinit var viewModel: PostsActivityViewModel

    private val ioTestScheduler = TestScheduler()

    @Test
    fun testViewModel() {

        MockitoAnnotations.initMocks(this)

        viewModel = PostsActivityViewModel(
                view,
                EmptyDataRepositoryImpl(),
                ioTestScheduler,
                Schedulers.trampoline())
        assertEquals(0, viewModel.posts.get().size)
    }

    @Test
    fun testCreateViewModel() {

        MockitoAnnotations.initMocks(this)

        viewModel = PostsActivityViewModel(
                view,
                EmptyDataRepositoryImpl(),
                ioTestScheduler,
                Schedulers.trampoline())
        assertEquals(0, viewModel.posts.get().size)

        viewModel.onCreate()
        assertEquals(0, viewModel.posts.get().size)
    }
}