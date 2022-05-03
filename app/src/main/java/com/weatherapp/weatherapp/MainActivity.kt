package com.weatherapp.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weatherapp.search.SearchViewModal
import com.weatherapp.weather.WeatherDetailActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val platziInfoActionModal = SearchViewModal()
        platziInfoActionModal.show(supportFragmentManager, platziInfoActionModal.tag)
    }
}