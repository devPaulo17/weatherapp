package com.weatherapp.data.remote.search

import com.weatherapp.data.remote.retrofit.SearchApiService
import com.weatherapp.data.remote.retrofit.executeRetrofitRequest
import com.weatherapp.data.remote.retrofit.handleResultRetrofit
import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.entities.search.Search
import com.weatherapp.domain.repositories.search.RemoteSearchDataSource

class RemoteSearchDataSourceImpl( private val rocketsApiService: SearchApiService) : RemoteSearchDataSource {

    override suspend fun getResults(): HandleResult<List<Search>> {
        val result = executeRetrofitRequest {
            rocketsApiService.getResults()
        }

        return handleResultRetrofit(result) { results ->
            results.toListResults()
        }
    }
}