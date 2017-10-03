package com.example.twcgilbert.postsapp.ui.posts

import android.os.SystemClock
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.azimolabs.conditionwatcher.ConditionWatcher
import com.azimolabs.conditionwatcher.Instruction
import com.example.twcgilbert.postsapp.R
import com.example.twcgilbert.postsapp.TestApp
import com.example.twcgilbert.postsapp.ui.posts.adapter.PostItemViewHolder
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
        ConditionWatcher.waitForCondition(NonEmptyRecyclerViewInstruction())

        onView(withId(R.id.recyclerView)).perform(swipeUp())

        SystemClock.sleep(2000)

        onView(withId(R.id.recyclerView)).perform(swipeDown())

        SystemClock.sleep(2000)

        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<PostItemViewHolder>(3, click()))

        SystemClock.sleep(2000)
    }

    inner class NonEmptyRecyclerViewInstruction : Instruction() {
        override fun getDescription() = "Wait for non-empty recyclerView"

        override fun checkCondition(): Boolean {
            var retVal = false
            val testApp = InstrumentationRegistry.getTargetContext().applicationContext as? TestApp
            if (null != testApp) {
                val recyclerView = testApp.currentActivity?.findViewById<RecyclerView>(R.id.recyclerView)
                if (null != recyclerView) {
                    val adapter = recyclerView.adapter
                    if (null != adapter) {
                        retVal = adapter.itemCount > 0
                    }
                }
            }
            return retVal
        }
    }
}