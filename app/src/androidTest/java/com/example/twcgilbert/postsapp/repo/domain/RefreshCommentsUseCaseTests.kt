package com.example.twcgilbert.postsapp.repo.domain

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.twcgilbert.postsapp.repo.network.DtoTestConstants
import com.example.twcgilbert.postsapp.repo.network.PostsService
import com.example.twcgilbert.postsapp.repo.network.PostsServiceFake
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants
import com.example.twcgilbert.postsapp.repo.persistence.LocalDatabase
import com.example.twcgilbert.postsapp.repo.persistence.model.CommentEntity
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RefreshCommentsUseCaseTests {

    private lateinit var api: PostsService
    private lateinit var mapper: Mapper
    private lateinit var db: LocalDatabase

    @Before
    fun setup() {
        mapper = Mapper()
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                LocalDatabase::class.java)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build()
        // prime our database with a test user
        db.userDao().insert(DaoTestConstants.USER)
        // and a test post
        db.postDao().insert(DaoTestConstants.POST)
    }

    @After
    fun teardown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun emptyRepo() {
        expectNoComments()
    }

    @Test
    fun notEmptyRepoAfterExecute() {
        expectNoComments()

        api = PostsServiceFake()
        val refreshCommentsUseCase = RefreshCommentsUseCaseImpl(api, mapper, db)

        expectNoComments()

        refreshCommentsUseCase
                .execute(DaoTestConstants.POST.id)
                .blockingAwait()

        expectComments()
    }

    fun expectNoComments() {
        val res = db.commentDao().getByPostId(DaoTestConstants.POST.id)
                .blockingFirst()
        assertEquals(res, emptyList<CommentEntity>())
    }

    fun expectComments() {
        val res = mapper.mapCommentDtos(DtoTestConstants.comments)
        val ret = db.commentDao().getByPostId(DaoTestConstants.POST.id)
                .blockingFirst()
        assertEquals(res, ret)
    }
}