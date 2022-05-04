package com.weatherapp.data

import com.weatherapp.data.remote.entities.search.SearchApi
import com.weatherapp.data.remote.entities.weather.ConsolidatedWeatherApi
import com.weatherapp.data.remote.entities.weather.WeatherApi
import com.weatherapp.data.remote.weather.consolidateMapper
import com.weatherapp.data.remote.weather.weatherMapper
import junit.framework.Assert.assertEquals
import org.junit.Test

class WeatherMapperTest {

    private val consolidatedWeather =
        listOf(
            ConsolidatedWeatherApi(
                "Test",
                "Test",
                "2022-05-07",
                9.7F,
                14.139999999999999F,
                14.76F,
                5.754410774739521F,
                71F
            ),
            ConsolidatedWeatherApi(
                "Test",
                "Test",
                "2022-05-07",
                9.7F,
                14.139999999999999F,
                14.76F,
                5.754410774739521F,
                71F
            ),
        )

    private val weatherLocationDetail = WeatherApi(
        "test",
        "City",
        368148,
        "4.656370,-74.117790",
        "Bogota/Colombia",
        consolidatedWeather,
        "2022-05-03T05:28:53.178071+01:00",
        "2022-05-03T05:28:53.178071+01:00"
    )


    @Test
    fun `transform to result weather detail`() {
        weatherLocationDetail.weatherMapper()
        with(weatherLocationDetail) {
            assertEquals(title, weatherLocationDetail.title)
            assertEquals(locationType, weatherLocationDetail.locationType)
            assertEquals(woeid, weatherLocationDetail.woeid)
            assertEquals(lattLong, weatherLocationDetail.lattLong)
            assertEquals(timeZone, weatherLocationDetail.timeZone)
            assertEquals(
                consolidatedWeather, weatherLocationDetail.consolidatedWeather
            )
        }
    }
}