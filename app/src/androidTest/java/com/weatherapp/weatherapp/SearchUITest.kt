package com.weatherapp.weatherapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.weatherapp.search.R
import com.weatherapp.search.SearchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)

    @Test
    fun searchWeather() {
        Espresso.onView(withId(R.id.editTextSearchWeather))
            .perform(
                ViewActions.typeText("London y esto es una prueba"),
                ViewActions.closeSoftKeyboard()
            )
    }
}