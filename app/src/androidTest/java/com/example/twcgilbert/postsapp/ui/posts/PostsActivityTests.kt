package com.example.twcgilbert.postsapp.ui.posts

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.azimolabs.conditionwatcher.ConditionWatcher
import com.example.twcgilbert.postsapp.R
import com.example.twcgilbert.postsapp.common.ui.NonEmptyRecyclerViewInstruction
import com.example.twcgilbert.postsapp.common.ui.RecyclerViewMatcher
import com.example.twcgilbert.postsapp.ui.posts.adapter.PostItemViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by twcgilbert on 02/10/2017.
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class PostsActivityTests {

    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    @get:Rule
    val activityTestRule = ActivityTestRule<PostsActivity>(PostsActivity::class.java)


    @Test
    fun swipePosts() {
        val position = 3
        val titleAtPosition = "Hi4"

        ConditionWatcher.waitForCondition(NonEmptyRecyclerViewInstruction())

        onView(withId(R.id.recyclerView)).perform(swipeUp())

        onView(withId(R.id.recyclerView)).perform(swipeDown())

        onView(withRecyclerView(R.id.recyclerView).atPosition(position))
                .check(matches(hasDescendant(withText(titleAtPosition))));

        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<PostItemViewHolder>(position, click()))

        // we should now be in the details view
        onView(withId(R.id.postTitleTextView)).check(matches(withText(titleAtPosition)))
    }
}