package com.weatherapp.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weatherapp.domain.entities.weather.ConsolidatedWeather
import com.weatherapp.weather.databinding.ItemForecastBinding

class WeatherForecastViewHolder(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup,
    private val binding: ItemForecastBinding = ItemForecastBinding.inflate(
        layoutInflater,
        parent,
        false
    )): RecyclerView.ViewHolder(binding.root) {

    fun bind(result: ConsolidatedWeather) {
        binding.apply {
            textviewTitle.text = result.weatherStateName
        }
    }
}