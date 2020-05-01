package com.example.nikechallenge

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.nikechallenge.view.MainActivity
import android.widget.AutoCompleteTextView
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@RunWith(AndroidJUnit4::class)

class SearchTest{

    @get:Rule var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSearchInput(){
        onView(ViewMatchers.withId(R.id.search_action))
            .perform(click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText("test"), ViewActions.pressImeActionButton())

        closeSoftKeyboard()

        Thread.sleep(1_000)
    }
}