package com.weatherapp.weatherapp

import com.weatherapp.data.remote.networkModule
import com.weatherapp.domain.di.repositoryDi
import com.weatherapp.search.searchModule
import com.weatherapp.weather.weatherModule

val MODULES_DI = listOf(
    networkModule,
    searchModule,
    weatherModule,
    repositoryDi
)