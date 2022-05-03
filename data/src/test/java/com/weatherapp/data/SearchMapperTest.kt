package com.weatherapp.data

import com.weatherapp.data.remote.entities.search.SearchApi
import com.weatherapp.data.remote.search.toListResults
import com.weatherapp.domain.entities.search.Search
import junit.framework.Assert.assertEquals
import org.junit.Test

class SearchMapperTest {

    private val searchResults = listOf(
        SearchApi(
            "test",
            "City",
            368148,
            "4.656370,-74.117790"
        )
    )


    @Test
    fun `transform to result weather detail()`() {
        searchResults.toListResults()
            .forEachIndexed { index, result ->
                with(searchResults[index]) {
                    assertEquals(title, result.title)
                    assertEquals(locationType, result.locationType)
                    assertEquals(woeid, result.woeid)
                    assertEquals(lattLong, result.lattLong)
                }
            }
    }
}