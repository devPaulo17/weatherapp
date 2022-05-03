package com.weatherapp.domain.repositories.weather

import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.entities.search.Search
import com.weatherapp.domain.entities.weather.Weather

interface RemoteWeatherDataSource {
    suspend fun getDetailLocationWeather(): HandleResult<Weather>
}