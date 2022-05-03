package com.weatherapp.search

import com.weatherapp.domain.entities.search.Search

sealed class SearchUiState{
    object Loading : SearchUiState()
    data class ListCategories(val data: List<Search>) : SearchUiState()
    object Error : SearchUiState()
    object ErrorConnection : SearchUiState()
}
