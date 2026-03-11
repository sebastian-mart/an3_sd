package com.sd.laborator.services

import com.sd.laborator.interfaces.TimeServiceInterface
import com.sd.laborator.pojo.WeatherForecastData
import io.github.damir.denis.tudor.spring.aop.chain.annotation.ChainStep
import io.github.damir.denis.tudor.spring.aop.chain.interfaces.Chainable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*
@Service
@ChainStep(next = CheckTempService::class)
open class TimeService : TimeServiceInterface, Chainable<String, String>{
    override fun getCurrentTime():String {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        return formatter.format(Date())
    }

    override fun proceed(input: String): String {
        if(input.contains("Error")){
            return input
        }
        else
        {
            try {
                val weatherData = Json.decodeFromString<WeatherForecastData>(input)
                weatherData.date = getCurrentTime()
                return Json.encodeToString(weatherData)
            }catch (e:Exception){
                return "Error: could not process timeservice!"+e.stackTraceToString()
            }
        }
    }
}