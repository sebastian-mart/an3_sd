package com.sd.laborator.pojo

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class CityInfoData(
    val lon: Double?,
    val lat: Double?,
    val name: String?
    )