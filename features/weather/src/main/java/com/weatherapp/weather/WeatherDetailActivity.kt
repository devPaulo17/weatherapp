package com.weatherapp.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.weatherapp.domain.entities.weather.Weather
import com.weatherapp.weather.databinding.ActivityWeatherDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
        getWeatherDetail()
        setListeners()
    }

    private fun getWeatherDetail() {
        val woeidLocation = intent.getIntExtra("woeidLocation", 0)
        weatherViewModel.getLocationWeatherDetail(woeidLocation)
    }

    private fun setListeners() {
        binding?.imageViewRowBack?.setOnClickListener {
            finish()
        }
    }

    private fun setUpRecyclerView() {
        binding?.recyclerForecastList?.apply {
            adapter = weatherForecastAdapter
            itemAnimator = null
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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
        binding?.progressBarWeatherData?.visibility = View.VISIBLE
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setWeatherData(result: Weather) {
        setDataCurrentLocation(result)
        weatherForecastAdapter.setResultList(result.consolidatedWeather)
        weatherForecastAdapter.notifyDataSetChanged()
        binding?.containerWeatherData?.visibility = View.VISIBLE
        binding?.progressBarWeatherData?.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    private fun setDataCurrentLocation(result: Weather) {
        binding?.apply {
            textViewTitle.text = result.title
            textViewLocationType.text = result.locationType
            textViewTime.text = result.timeZone
            textViewTemperature.text =
                roundNumber(result.consolidatedWeather[0].theTemp.toInt()).toString()
            textViewTemperatureMax.text =
                "${roundNumber(result.consolidatedWeather[0].maxTemp.toInt()).toString()}°C"
            textViewTemperatureMin.text =
                "${roundNumber(result.consolidatedWeather[0].maxTemp.toInt()).toString()}°C"
            textViewWeatherState.text = result.consolidatedWeather[0].weatherStateName
            textViewWindState.text =
                "${roundNumber(result.consolidatedWeather[0].windSpeed.toInt()).toString()}km/h"
            textViewHumidityState.text =
                "${roundNumber(result.consolidatedWeather[0].humidity.toInt()).toString()}%"

            imageViewGraph.setBackgroundResource(getWeatherStateIcon(result))
        }
    }

    private fun setErrorState() {
    }

    //Esta función no existiría si las iconos llegaran desde el backend. Sería una mejora
    private fun getWeatherStateIcon(result: Weather): Int {
        return when (result.consolidatedWeather[0].weatherStateAbbr) {
            WeatherStateCode.SHOWERS.type -> {
                R.drawable.ic_s
            }
            WeatherStateCode.SNOW.type -> {
                R.drawable.ic_sn
            }
            WeatherStateCode.SLEET.type -> {
                R.drawable.ic_lc
            }
            WeatherStateCode.HAIL.type -> {
                R.drawable.ic_h
            }
            WeatherStateCode.THUNDERSTORM.type -> {
                R.drawable.ic_t
            }
            WeatherStateCode.HEAVY_RAIN.type -> {
                R.drawable.ic_hr
            }
            WeatherStateCode.LIGHT_RAIN.type -> {
                R.drawable.ic_lr
            }
            WeatherStateCode.HEAVY_CLOUD.type -> {
                R.drawable.ic_hc
            }
            WeatherStateCode.LIGHT_CLOUD.type -> {
                R.drawable.ic_lc
            }
            WeatherStateCode.CLEAR.type -> {
                R.drawable.ic_c
            }
            else -> {
                R.drawable.ic_c
            }
        }
    }

    private fun roundNumber(number: Int): BigDecimal? =
        BigDecimal(number).setScale(0, RoundingMode.HALF_EVEN)

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}