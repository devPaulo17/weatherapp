package com.weatherapp.data.remote.entities.weather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class ConsolidatedWeatherApi(
    @SerializedName("weather_state_name")
    val weatherStateName: String,
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String,
    @SerializedName("applicable_date")
    val applicableDate: String,
    @SerializedName("the_temp")
    val theTemp: Float,
    @SerializedName("min_temp")
    val minTemp: Float,
    @SerializedName("max_temp")
    val maxTemp: Float,
    @SerializedName("wind_speed")
    val windSpeed: Float,
    val humidity: Float,
)
