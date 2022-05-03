package com.weatherapp.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.entities.weather.ConsolidatedWeather
import com.weatherapp.domain.entities.weather.Weather
import com.weatherapp.domain.repositories.weather.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import kotlin.test.assertTrue

class WeatherViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()

    private val consolidatedWeather =
        listOf(ConsolidatedWeather("Test", "Test"), ConsolidatedWeather("Test", "Test"))

    private val weatherLocationDetail = Weather(
        "test",
        "City",
        368148,
        "4.656370,-74.117790",
        "Bogota/Colombia",
        consolidatedWeather
    )


    private val mockResultsRepository = mock<WeatherRepository> {
        onBlocking { getDetailLocationWeather(woeidLocation) } doReturn HandleResult.Success(weatherLocationDetail)
    }

    private val viewModel =
        WeatherViewModel(mockResultsRepository)


    @Test
    fun `getDetailLocationWeather() when use case returns HandleResult Success then state emit a search results`() {
        val spyLiveData: Observer<WeatherhUiState> = spy(Observer { })
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.getLocationWeatherDetail(woeidLocation)
            assertTrue(viewModel.viewState.value is WeatherhUiState.ListCategories)
        }
    }

   /* @Test
    fun `getDetailLocationWeather() when use case returns HandleResult error then state emit a search error`() {
        val spyLiveData: Observer<WeatherhUiState> = spy(Observer { })
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.getLocationWeatherDetail()
            assertTrue(viewModel.viewState.value is WeatherhUiState.ListCategories)
        }
    }*/
}