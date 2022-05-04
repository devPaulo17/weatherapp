package com.weatherapp.data.remote.entities.weather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherApi(
    var title: String,
    @SerializedName("location_type")
    val locationType: String? = "",
    val woeid: Int,
    @SerializedName("latt_long")
    var lattLong: String? = "",
    @SerializedName("timezone")
    val timeZone: String? = "",
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeatherApi> = listOf(),
    @SerializedName("sun_rise")
    val sunRise: String,
    @SerializedName("sun_set")
    val sunSet: String
)