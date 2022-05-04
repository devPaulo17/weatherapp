package com.weatherapp.data.remote.retrofit

import com.weatherapp.data.remote.entities.weather.WeatherApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiService{

    @GET("api/location/{woeid}")
    suspend fun getResults(@Path("woeid") woeidLocation: Int): Response<WeatherApi>
}