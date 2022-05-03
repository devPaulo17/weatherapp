package com.weatherapp.data.remote.retrofit

import com.weatherapp.data.remote.entities.search.SearchApi
import retrofit2.Response
import retrofit2.http.GET


interface SearchApiService{

    @GET("api/location/search/?query=l")
    suspend fun getResults(): Response<List<SearchApi>>
}