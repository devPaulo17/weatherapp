package com.weatherapp.data.remote.retrofit

import com.weatherapp.data.remote.entities.search.SearchApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchApiService{

    @GET("api/location/search/?")
    suspend fun getResults(@Query("query")searchText: String): Response<List<SearchApi>>
}