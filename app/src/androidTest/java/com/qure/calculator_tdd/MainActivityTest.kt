package com.qure.calculator_tdd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testStartOperator() {
        onView(withId(R.id.mainActivity_button_equal)).perform(click())
        onView(withId(R.id.mainActivity_textView_operator)).check(matches(withText("0")))
        onView(withId(R.id.mainActivity_button_plus)).perform(click())
        onView(withId(R.id.mainActivity_textView_operator)).check(matches(withText("0")))
        onView(withId(R.id.mainActivity_button_minus)).perform(click())
        onView(withId(R.id.mainActivity_textView_operator)).check(matches(withText("0")))
        onView(withId(R.id.mainActivity_button_mul)).perform(click())
        onView(withId(R.id.mainActivity_textView_operator)).check(matches(withText("0")))
        onView(withId(R.id.mainActivity_button_div)).perform(click())
        onView(withId(R.id.mainActivity_textView_operator)).check(matches(withText("0")))
    }

    @Test
    fun testStartNumber() {
        onView(withId(R.id.mainActivity_button_one)).perform(click())
        onView(withId(R.id.mainActivity_textView_operator)).check(matches(withText("1")))
    }
}