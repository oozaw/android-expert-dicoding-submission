package com.dicoding.gt

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dicoding.gt.detail.DetailActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
   @get:Rule
   val activity = ActivityScenarioRule(MainActivity::class.java)

   @Test
   fun getNews_Success() {
      Espresso.onView(withId(R.id.rv_news))
         .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
      Espresso.onView(withId(R.id.rv_news))
         .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
   }

   @Test
   fun loadDetailNews_Success() {
      Intents.init()
      Espresso.onView(withId(R.id.rv_news))
         .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
      Espresso.onView(withId(R.id.rv_news)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3,
         ViewActions.click()
      ))
      Intents.intended(hasComponent(DetailActivity::class.java.name))
      Espresso.onView(withId(R.id.iv_thumbnail))
         .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
      Espresso.onView(withId(R.id.tv_content))
         .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
   }
}