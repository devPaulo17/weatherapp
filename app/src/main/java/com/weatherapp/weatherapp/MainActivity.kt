package com.weatherapp.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weatherapp.search.SearchViewModal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val platziInfoActionModal = SearchViewModal()
        platziInfoActionModal.show(supportFragmentManager, platziInfoActionModal.tag)
    }
}