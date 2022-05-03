package com.weatherapp.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weatherapp.weather.databinding.ActivityWeatherDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherDetailActivity : AppCompatActivity() {

    private var binding: ActivityWeatherDetailBinding? = null
    private val searchViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
        searchViewModel.getLocationWeatherDetail()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}