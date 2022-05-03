package com.weatherapp.domain.repositories.weather

import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.entities.weather.Weather

class WeatherRepositoryImpl(private val remoteDatasource: RemoteWeatherDataSource) :
    WeatherRepository {

    override suspend fun getDetailLocationWeather(woeidLocation: Int): HandleResult<Weather> = remoteDatasource.getDetailLocationWeather(woeidLocation)

}