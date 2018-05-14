package com.example.twcgilbert.postsapp.repo.persistence

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.database.sqlite.SQLiteConstraintException
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.twcgilbert.postsapp.repo.model.Post
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.POST
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.POST1
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.POST2
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.POST3
import com.example.twcgilbert.postsapp.repo.persistence.DaoTestConstants.Companion.USER
import org.junit.*
import org.junit.runner.RunWith

/**
 * Test the implementation of [PostDao]
 */
@RunWith(AndroidJUnit4::class)
class PostDaoTest {

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
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getPostWhenNoPostInserted() {
        database.postDao().getById(POST.id)
                .test()
                .assertNoValues()
    }

    @Test
    fun insertAndGetPost() {
        // When inserting a new post in the data source
        database.postDao().insertAll(arrayListOf(POST))

        // When subscribing to the emissions of the post
        database.postDao().getById(POST.id)
                .test()
                // assertValue asserts that there was only one emission of the post
                .assertValue { it == POST }
    }

    @Test(expected = SQLiteConstraintException::class)
    fun insertPostForUnknownUser() {
        // When inserting a new post in the data source
        database.postDao().insert(POST2)
    }

    @Test
    fun insertAndGetPosts() {
        val list = arrayListOf(POST, POST3)
        // When inserting a new post in the data source
        database.postDao().insertAll(list)

        // When subscribing to the emissions of the post
        database.postDao().getAll()
                .test()
                .assertValues(list)
    }

    @Test
    fun insertAndGetPostsDenormalised() {
        val list = arrayListOf(POST, POST3)
        // When inserting a new post in the data source
        database.postDao().insertAll(list)

        // When subscribing to the emissions of the post
        val resultList = arrayListOf(
                Post(POST.id, POST.userId, POST.title, POST.body, USER.name, USER.email),
                Post(POST3.id, POST3.userId, POST3.title, POST3.body, USER.name, USER.email)
        )
        database.postDao().getAllDenormalised()
                .test()
                .assertValues(resultList)
    }

    @Test
    fun updateAndGetPost() {
        // Given that we have a post in the data source
        database.postDao().insert(POST)

        // When we are updating the name of the post
        database.postDao().insert(POST1)

        // both comments have the same id
        Assert.assertEquals(POST.id, POST1.id)
        // but are not otherwise equal
        Assert.assertNotEquals(POST, POST1)

        // When subscribing to the emissions of the post
        database.postDao().getById(POST.id)
                .test()
                // assertValue asserts that there was only one emission of the post
                .assertValue { it == POST1 && it != POST }
    }

    @Test
    fun deleteAndGetPost() {
        // Given that we have a post in the data source
        database.postDao().insert(POST)

        //When we are deleting all post
        database.postDao().deleteAll()

        // When subscribing to the emissions of the post
        database.postDao().getById(POST.id)
                .test()
                // check that there's no post emitted
                .assertNoValues()
    }

    @Test
    fun deleteUsersAndGetPost() {
        // Given that we have a post in the data source
        database.postDao().insert(POST)

        //When we are deleting USER
        database.userDao().deleteAll()

        // When subscribing to the emissions of the post
        database.postDao().getById(POST.id)
                .test()
                // check that there's no post emitted
                .assertNoValues()
    }
}