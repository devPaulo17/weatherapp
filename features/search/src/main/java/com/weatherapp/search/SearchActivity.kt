package com.weatherapp.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weatherapp.search.databinding.ActivitySearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private var binding: ActivitySearchBinding? = null
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        searchViewModel.getResults()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}