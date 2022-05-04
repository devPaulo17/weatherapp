package com.weatherapp.data.remote.search

import com.weatherapp.data.remote.entities.search.SearchApi
import com.weatherapp.domain.entities.search.Search


fun List<SearchApi>.toListResults(): List<Search> {
    return map { item ->
        Search(
            title = item.title,
            locationType = item.locationType,
            woeid = item.woeid,
            lattLong = item.lattLong
        )
    }
}