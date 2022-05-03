package com.weatherapp.domain.repositories.search

import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.entities.search.Search

class SearchRepositoryImpl(private val remoteDataSource: RemoteSearchDataSource): SearchRepository {

    override suspend fun getResults(): HandleResult<List<Search>> = remoteDataSource.getResults()
}