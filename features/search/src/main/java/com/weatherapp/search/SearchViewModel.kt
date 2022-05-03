package com.weatherapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.repositories.search.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _viewState = MutableLiveData<SearchUiState>(SearchUiState.Loading)
    val viewState: LiveData<SearchUiState> = _viewState

    fun getResults() {
        viewModelScope.launch {
            _viewState.value = when (val result = searchRepository.getResults()) {
                HandleResult.Loading -> SearchUiState.Loading
                is HandleResult.Success -> {
                    SearchUiState.ListCategories(result.data)
                }
                is HandleResult.InternetConnectionError -> SearchUiState.ErrorConnection
                is HandleResult.Error -> SearchUiState.Error
            }
        }
    }
}