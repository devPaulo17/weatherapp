package com.weatherapp.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weatherapp.search.SearchViewModal
import com.weatherapp.weather.WeatherDetailActivity
import com.weatherapp.weather.databinding.ActivityWeatherDetailBinding
import com.weatherapp.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.buttonSearch?.setOnClickListener {
            val platziInfoActionModal = SearchViewModal()
            platziInfoActionModal.show(supportFragmentManager, platziInfoActionModal.tag)

        }
    }
}