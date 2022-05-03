package com.weatherapp.data.remote.entities.search

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchApi(
    var title : String,
    @SerializedName("location_type")
    var locationType: String? = "",
    var woeid: Int,
    @SerializedName("latt_long")
    var lattLong: String? = ""
)