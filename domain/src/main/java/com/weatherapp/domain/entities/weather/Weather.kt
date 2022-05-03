package com.weatherapp.domain.entities.weather

data class Weather(
    var title: String,
    val locationType: String,
    val woeid: Int,
    var lattLong: String,
    val timeZone: String,
    val consolidatedWeather: List<ConsolidatedWeather> = listOf()
)
