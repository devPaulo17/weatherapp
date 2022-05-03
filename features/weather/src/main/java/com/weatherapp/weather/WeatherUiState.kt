package com.weatherapp.weather

import com.weatherapp.domain.entities.weather.Weather

sealed class WeatherhUiState{
    object Loading : WeatherhUiState()
    data class WeatherDetail(val data: Weather) : WeatherhUiState()
    object Error : WeatherhUiState()
    object ErrorConnection : WeatherhUiState()
}