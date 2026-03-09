package com.sd.laborator.services

import com.sd.laborator.interfaces.CheckTempInterface
import com.sd.laborator.pojo.WeatherForecastData
import org.springframework.stereotype.Service

@Service
class CheckTempService : CheckTempInterface{
    override fun isValidTemp(weatherData: WeatherForecastData): Boolean {
        return if(weatherData.currentTemp>20) true else false
    }
}