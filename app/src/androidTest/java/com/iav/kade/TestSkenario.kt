package com.iav.kade

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.iav.kade.activity.HomeActivity
import com.iav.kade.R.id.add_to_favorite
import com.iav.kade.R.id.fav
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestSkenario {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)
    @Test
    fun behaviorTest(){
        Thread.sleep(3000)

        val recyclerView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.rv),
                        ViewMatchers.isDisplayed()))


        recyclerView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        recyclerView
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, ViewActions.click()))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())

        Espresso.pressBack()


        Espresso.onView(ViewMatchers.withId(fav)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click())
        recyclerView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        recyclerView
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        Espresso.pressBack()
        Thread.sleep(1000)

        val linearLayout3 = Espresso.onView(
                Matchers.allOf(childAtPosition(
                        childAtPosition(
                                ViewMatchers.withId(R.id.rv),
                                0),
                        0),
                        ViewMatchers.isDisplayed()))
        linearLayout3.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}