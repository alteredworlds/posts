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

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.USER
import com.example.twcgilbert.postsapp.repo.persistence.model.UserEntity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test the implementation of [UserDao]
 */
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

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
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getUsersWhenNoUserInserted() {
        database.userDao().getById(USER.id)
                .test()
                .assertNoValues()
    }

    @Test
    fun insertAndGetUser() {
        // When inserting a new user in the data source
        database.userDao().insertAll(arrayListOf(USER))

        // When subscribing to the emissions of the user
        database.userDao().getById(USER.id)
                .test()
                // assertValue asserts that there was only one emission of the user
                .assertValue { it == USER }
    }

    @Test
    fun updateAndGetUser() {
        // Given that we have a user in the data source
        database.userDao().insert(USER)

        // When we are updating the name of the user
        val updatedUser = UserEntity(USER.id, "new name")
        database.userDao().insert(updatedUser)

        // When subscribing to the emissions of the user
        database.userDao().getById(USER.id)
                .test()
                // assertValue asserts that there was only one emission of the user
                .assertValue { it.id == USER.id && it.name == "new name" }
    }

    @Test
    fun deleteAndGetUser() {
        // Given that we have a user in the data source
        database.userDao().insert(USER)

        //When we are deleting all users
        database.userDao().deleteAll()

        // When subscribing to the emissions of the user
        database.userDao().getById(USER.id)
                .test()
                // check that there's no user emitted
                .assertNoValues()
    }
}
