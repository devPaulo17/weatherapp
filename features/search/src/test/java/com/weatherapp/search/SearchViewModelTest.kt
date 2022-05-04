package com.weatherapp.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.entities.search.Search
import com.weatherapp.domain.repositories.search.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.spy
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


import kotlin.test.assertTrue


class SearchViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()

    private val searchResultList = listOf(
        Search(
            "test",
            "City",
            368148,
            "4.656370,-74.117790"
        )
    )

    private val mockResultsRepository = mock<SearchRepository> {
        onBlocking { getResults("london") } doReturn HandleResult.Success(searchResultList)
    }

    private val viewModel =
        SearchViewModel(mockResultsRepository)


    @Test
    fun `getResults() when use case returns HandleResult Success then state emit a search results`() {
        val spyLiveData: Observer<SearchUiState> = spy(Observer { })
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.getResults("london")
            assertTrue(viewModel.viewState.value is SearchUiState.SearchResultList)
        }
    }
}