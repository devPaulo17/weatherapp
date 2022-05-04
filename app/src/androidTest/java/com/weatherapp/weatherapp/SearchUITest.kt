package com.weatherapp.weatherapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.weatherapp.search.R
import com.weatherapp.search.SearchActivity
import com.weatherapp.search.SearchResultsListViewHolder
import com.weatherapp.weather.WeatherDetailActivity
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Matcher

@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchUITest {
    @get:Rule
    val activityRuleSearch = ActivityScenarioRule(SearchActivity::class.java)


    @Test
    fun verifyEmptyState() {

        onView(withId(R.id.editTextSearchWeather)).perform(
            ViewActions.typeText("test"),
            ViewActions.closeSoftKeyboard()
        )

        onView(withId(R.id.empty_state_label)).check(matches(withText(containsString("No pudimos encontrar la ubicación"))))

    }

    @Test
    fun clickItemSearchResult() {

        onView(withId(R.id.editTextSearchWeather)).perform(
            ViewActions.typeText("lon"),
            ViewActions.closeSoftKeyboard()
        )
        Thread.sleep(5000)

        onView(withId(R.id.search_result))
            .perform(
                actionOnItemAtPosition<SearchResultsListViewHolder>(
                    0,
                    click()
                )
            )
        Thread.sleep(5000)

        onView(withId(com.weatherapp.weather.R.id.textView_location_type)).check(matches(withText("City")))
    }
}

