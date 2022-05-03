package com.weatherapp.domain.di

import com.weatherapp.domain.repositories.search.SearchRepository
import com.weatherapp.domain.repositories.search.SearchRepositoryImpl
import com.weatherapp.domain.repositories.weather.WeatherRepository
import com.weatherapp.domain.repositories.weather.WeatherRepositoryImpl
import org.koin.dsl.module

val repositoryDi = module {
    factory<SearchRepository> {
        SearchRepositoryImpl(get())
    }

    factory<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }
}