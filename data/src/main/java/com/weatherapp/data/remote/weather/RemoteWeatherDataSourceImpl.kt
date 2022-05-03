package com.weatherapp.data.remote.weather

import com.weatherapp.data.remote.retrofit.WeatherApiService
import com.weatherapp.data.remote.retrofit.executeRetrofitRequest
import com.weatherapp.data.remote.retrofit.handleResultRetrofit
import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.entities.weather.Weather
import com.weatherapp.domain.repositories.weather.RemoteWeatherDataSource

class RemoteWeatherDataSourceImpl(private val weatherApiService: WeatherApiService) :
    RemoteWeatherDataSource {

    override suspend fun getDetailLocationWeather(woeidLocation: Int): HandleResult<Weather> {
        val retrofitExecution = executeRetrofitRequest {
            weatherApiService.getResults(woeidLocation)
        }
        return handleResultRetrofit(retrofitExecution) {
            it.weatherMapper()
        }
    }
}