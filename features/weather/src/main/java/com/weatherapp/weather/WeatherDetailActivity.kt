package com.weatherapp.weather

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.weatherapp.domain.entities.weather.Weather
import com.weatherapp.weather.databinding.ActivityWeatherDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherDetailActivity : AppCompatActivity() {

    private var binding: ActivityWeatherDetailBinding? = null
    private val weatherViewModel: WeatherViewModel by viewModel()
    private var weatherForecastAdapter = WeatherForecastAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpRecyclerView()
        searchResultsObserver()
        val woeidLocation = intent.getIntExtra("woeidLocation", 0)

        weatherViewModel.getLocationWeatherDetail(44418)

    }

    private fun setUpRecyclerView() {
        binding?.recyclerForecastList?.apply {
            adapter = weatherForecastAdapter
            itemAnimator = null
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        }
    }

    private fun searchResultsObserver() {
        weatherViewModel.viewState.observe(this, ::handleUiState)
    }


    private fun handleUiState(state: WeatherhUiState) {
        when (state) {
            is WeatherhUiState.WeatherDetail -> setWeatherData(state.data)
            is WeatherhUiState.Loading -> showLoadingState()
            else -> {
                setErrorState()
            }
        }
    }

    private fun showLoadingState() {
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setWeatherData(result: Weather) {
        setDataCurrentLocation(result)
        weatherForecastAdapter.setResultList(result.consolidatedWeather)
        weatherForecastAdapter.notifyDataSetChanged()
    }

    private fun setDataCurrentLocation(result: Weather) {
        binding?.apply {
            textViewTitle.text = result.title
            textViewLocationType.text = result.locationType
            textViewTime.text = result.timeZone
        }
    }

    private fun setErrorState() {


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}