package com.weatherapp.domain.entities.weather

data class ConsolidatedWeather(
    val weatherStateName: String,
    val weatherStateAbbr: String,
    val applicableDate: String,
    val theTemp:Float,
    val minTemp: Float,
    val maxTemp: Float,
    val windSpeed: Float,
    val humidity: Float
)
