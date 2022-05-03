package com.weatherapp.domain.entities.search

data class Search(
    var title : String,
    var locationType: String,
    var woeid: Int,
    var lattLong: String
)
