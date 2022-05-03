package com.weatherapp.domain.repositories.search

import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.entities.search.Search

interface SearchRepository {
    suspend fun getResults(): HandleResult<List<Search>>
}