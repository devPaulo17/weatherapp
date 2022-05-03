package com.weatherapp.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.domain.HandleResult
import com.weatherapp.domain.repositories.weather.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository): ViewModel() {

    private val _viewState = MutableLiveData<WeatherhUiState>(WeatherhUiState.Loading)
    val viewState: LiveData<WeatherhUiState> = _viewState

    fun getLocationWeatherDetail(woeidLocation: Int) {
        viewModelScope.launch {
            _viewState.value = when (val result = weatherRepository.getDetailLocationWeather(woeidLocation)) {
                HandleResult.Loading -> WeatherhUiState.Loading
                is HandleResult.Success -> {
                    WeatherhUiState.WeatherDetail(result.data)
                }
                is HandleResult.InternetConnectionError -> WeatherhUiState.ErrorConnection
                is HandleResult.Error -> WeatherhUiState.Error
            }
        }
    }


}