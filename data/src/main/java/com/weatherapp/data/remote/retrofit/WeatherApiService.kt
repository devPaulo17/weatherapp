package com.weatherapp.data.remote.retrofit

import com.weatherapp.data.remote.entities.search.SearchApi
import com.weatherapp.data.remote.entities.weather.WeatherApi
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApiService{

    @GET("api/location/44418")
    suspend fun getResults(): Response<WeatherApi>
}