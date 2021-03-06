package com.weatherapp.data.remote.search

import com.weatherapp.data.remote.retrofit.SearchApiService
import com.weatherapp.data.remote.retrofit.executeRetrofitRequest
import com.weatherapp.data.remote.retrofit.handleResultRetrofit
import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.entities.search.Search
import com.weatherapp.domain.repositories.search.RemoteSearchDataSource

class RemoteSearchDataSourceImpl( private val searchApiService: SearchApiService) : RemoteSearchDataSource {

    override suspend fun getResults(searchText: String): HandleResult<List<Search>> {
        val result = executeRetrofitRequest {
            searchApiService.getResults(searchText)
        }

        return handleResultRetrofit(result) { results ->
            results.toListResults()
        }
    }
}