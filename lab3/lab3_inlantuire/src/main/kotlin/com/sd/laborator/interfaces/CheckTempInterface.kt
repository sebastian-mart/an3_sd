package com.sd.laborator.interfaces

import com.sd.laborator.pojo.WeatherForecastData

interface CheckTempInterface {
    fun isValidTemp(temp: Int): Boolean
}