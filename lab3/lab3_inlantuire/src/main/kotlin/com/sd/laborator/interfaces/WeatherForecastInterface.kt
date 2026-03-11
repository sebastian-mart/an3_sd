package com.sd.laborator.interfaces
import com.sd.laborator.pojo.CityInfoData
import com.sd.laborator.pojo.WeatherForecastData
interface WeatherForecastInterface {
    fun getForecastData(info: String): String
}