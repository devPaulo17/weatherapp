package com.weatherapp.weather

enum class WeatherStateCode(var type: String) {
    SNOW("sn"),
    SLEET("sl"),
    HAIL("h"),
    THUNDERSTORM("t"),
    HEAVY_RAIN("hr"),
    LIGHT_RAIN("lr"),
    SHOWERS("s"),
    HEAVY_CLOUD("hc"),
    LIGHT_CLOUD("lc"),
    CLEAR("c"),

}