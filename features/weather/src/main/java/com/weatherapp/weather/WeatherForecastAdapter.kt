package com.weatherapp.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weatherapp.domain.entities.weather.ConsolidatedWeather

class WeatherForecastAdapter : RecyclerView.Adapter<WeatherForecastViewHolder>() {

    var forecastList = listOf<ConsolidatedWeather>()
        private set

    fun setResultList(value: List<ConsolidatedWeather>) {
        forecastList = value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WeatherForecastViewHolder(layoutInflater, parent)
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        val item = forecastList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = forecastList.size
}