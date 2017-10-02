package com.example.twcgilbert.postsapp

import android.os.SystemClock
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.action.ViewActions.swipeUp
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.twcgilbert.postsapp.ui.posts.PostsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by twcgilbert on 02/10/2017.
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class PostsActivityTests {

    @get:Rule
    val activityTestRule = ActivityTestRule<PostsActivity>(PostsActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun swipePosts() {
        SystemClock.sleep(2000)

        onView(withId(R.id.recyclerView)).perform(swipeUp())

        SystemClock.sleep(2000)

        onView(withId(R.id.recyclerView)).perform(swipeDown())

        SystemClock.sleep(2000)
    }
}