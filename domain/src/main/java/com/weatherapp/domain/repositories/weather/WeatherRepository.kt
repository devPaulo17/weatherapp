package com.weatherapp.domain.repositories.weather

import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.entities.search.Search
import com.weatherapp.domain.entities.weather.Weather

interface WeatherRepository {
    suspend fun getDetailLocationWeather(woeidLocation: Int): HandleResult<Weather>
}