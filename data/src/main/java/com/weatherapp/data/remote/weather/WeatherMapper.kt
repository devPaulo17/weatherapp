package com.weatherapp.data.remote.weather

import com.weatherapp.data.remote.entities.weather.ConsolidatedWeatherApi
import com.weatherapp.data.remote.entities.weather.WeatherApi
import com.weatherapp.domain.entities.weather.Weather
import com.weatherapp.domain.entities.weather.ConsolidatedWeather

fun WeatherApi.weatherMapper(): Weather {
    return consolidatedWeather?.consolidateMapper()?.let {
        Weather(
        title = title,
        locationType = locationType?:"",
        woeid = woeid,
        lattLong = lattLong?:"",
        timeZone = timeZone?:"",
        consolidatedWeather = it

    )
    }
}

fun List<ConsolidatedWeatherApi>.consolidateMapper(): List<ConsolidatedWeather> {
    return map { item ->
        ConsolidatedWeather(
            weatherStateName = item.weatherStateName,
            weatherStateAbbr = item.weatherStateAbbr
        )
    }
}