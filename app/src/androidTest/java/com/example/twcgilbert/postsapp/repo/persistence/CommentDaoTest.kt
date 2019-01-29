/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.twcgilbert.postsapp.repo.persistence

import android.database.sqlite.SQLiteConstraintException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.COMMENT
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.COMMENT1
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.COMMENT2
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.COMMENT3
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.POST
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.USER
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test the implementation of [CommentDao]
 */
@RunWith(AndroidJUnit4::class)
class CommentDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: LocalDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                LocalDatabase::class.java)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build()
        // prime our database with a test user
        database.userDao().insert(USER)
        // and a test post
        database.postDao().insert(POST)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getCommentWhenNoCommentInserted() {
        database.commentDao().getById(COMMENT.id)
                .test()
                .assertNoValues()
    }

    @Test
    fun insertAndGetComment() {
        // When inserting a new comment in the data source
        database.commentDao().insertAll(arrayListOf(COMMENT))

        // When subscribing to the emissions of the comment
        database.commentDao().getById(COMMENT.id)
                .test()
                // assertValue asserts that there was only one emission of the comment
                .assertValue { it == COMMENT }
    }

    @Test
    fun insertAndGetNumComments() {
        // When inserting a new comment in the data source
        database.commentDao().insertAll(arrayListOf(COMMENT, COMMENT3))

        // When subscribing to the emissions of the comment
        database.commentDao().getNumCommentsForPostId(COMMENT.postId)
                .test()
                // assertValue asserts that there was only one emission of the comment
                .assertValue { it == 2 }
    }

    @Test(expected = SQLiteConstraintException::class)
    fun insertCommentForUnknownPost() {
        // When inserting a new post in the data source
        database.commentDao().insert(COMMENT2)
    }

    @Test
    fun updateAndGetComment() {
        // Given that we have a comment in the data source
        database.commentDao().insert(COMMENT)

        // When we are updating the name of the comment
        database.commentDao().insert(COMMENT1)

        // both comments have the same id
        assertEquals(COMMENT.id, COMMENT1.id)
        // but are not otherwise equal
        assertNotEquals(COMMENT, COMMENT1)

        // When subscribing to the emissions of the comment
        database.commentDao().getById(COMMENT1.id)
                .test()
                // assertValue asserts that there was only one emission of the comment
                .assertValue { it == COMMENT1 && it != COMMENT }
    }

    @Test
    fun deleteAndGetComment() {
        // Given that we have a comment in the data source
        database.commentDao().insert(COMMENT)

        //When we are deleting all comments
        database.commentDao().deleteAll()

        // When subscribing to the emissions of the comment
        database.commentDao().getById(COMMENT.id)
                .test()
                // check that there's no comment emitted
                .assertNoValues()
    }

    @Test
    fun deletePostsAndGetComment() {
        // Given that we have a comment in the data source
        database.commentDao().insert(COMMENT)

        //When we are deleting all POSTS
        database.postDao().deleteAll()

        // When subscribing to the emissions of the comment
        database.commentDao().getById(COMMENT.id)
                .test()
                // check that there's no comment emitted
                .assertNoValues()
    }

    @Test
    fun deleteUsersAndGetComment() {
        // Given that we have a comment in the data source
        database.commentDao().insert(COMMENT)

        //When we are deleting all USERS
        database.userDao().deleteAll()

        // When subscribing to the emissions of the comment
        database.commentDao().getById(COMMENT.id)
                .test()
                // check that there's no comment emitted
                .assertNoValues()
    }
}
