package com.weatherapp.data.remote.weather


import com.weatherapp.data.remote.entities.weather.ConsolidatedWeatherApi
import com.weatherapp.data.remote.entities.weather.WeatherApi
import com.weatherapp.domain.entities.weather.Weather
import com.weatherapp.domain.entities.weather.ConsolidatedWeather

fun WeatherApi.weatherMapper(): Weather {
    return Weather(
        title = title,
        locationType = locationType?:"",
        woeid = woeid,
        lattLong = lattLong?:"",
        timeZone = timeZone?:"",
        consolidatedWeather = consolidatedWeather.consolidateMapper(),
        sunRise = sunRise,
        sunSet = sunSet
    )
}

fun List<ConsolidatedWeatherApi>.consolidateMapper(): List<ConsolidatedWeather> {
    return map { item ->
        ConsolidatedWeather(
            weatherStateName = item.weatherStateName,
            weatherStateAbbr = item.weatherStateAbbr,
            applicableDate = item.applicableDate,
            theTemp = item.theTemp,
            minTemp = item.minTemp,
            maxTemp = item.maxTemp,
            windSpeed = item.windSpeed,
            humidity = item.humidity
        )
    }
}