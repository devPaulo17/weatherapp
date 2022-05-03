package com.weatherapp.weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weatherapp.domain.entities.weather.ConsolidatedWeather
import com.weatherapp.domain.entities.weather.Weather
import com.weatherapp.weather.databinding.ItemForecastBinding
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class WeatherForecastViewHolder(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup,
    private val binding: ItemForecastBinding = ItemForecastBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun bind(result: ConsolidatedWeather) {
        binding.apply {
            textViewTemperatureForecast.text =
                "${BigDecimal(result.theTemp.toInt()).setScale(0, RoundingMode.HALF_EVEN)}°C"
            textViewWeekDay.text = getWeekDayName(result)

            imageViewGraphForecast.setBackgroundResource(getWeatherStateIcon(result))

        }
    }


    //Esta función no existiría si las iconos llegaran desde el backend. Sería una mejora
    private fun getWeatherStateIcon(result: ConsolidatedWeather): Int {
        return when (result.weatherStateAbbr) {
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

    @SuppressLint("SimpleDateFormat")
    private fun getWeekDayName(result: ConsolidatedWeather): String {
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date? = inFormat.parse(result.applicableDate)
        val outFormat = SimpleDateFormat("EEEE")
        return date?.let { outFormat.format(it) }.toString()

    }
}