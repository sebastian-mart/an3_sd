package com.sd.laborator.services

import com.sd.laborator.interfaces.CheckTempInterface
import com.sd.laborator.pojo.WeatherForecastData
import io.github.damir.denis.tudor.spring.aop.chain.annotation.ChainStep
import io.github.damir.denis.tudor.spring.aop.chain.interfaces.Chainable
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service

@ChainStep
@Service
open class CheckTempService : CheckTempInterface, Chainable<String, String>{
    override fun isValidTemp(temp:Int): Boolean {
        return if(temp>20) true else false
    }

    override fun proceed(input: String): String {
        if (input.contains("Error")) return input
        try {
            val weatherObj = Json.decodeFromString<WeatherForecastData>(input)
            return if (isValidTemp(weatherObj.currentTemp))
                weatherObj.toString()
            else
                weatherObj.toString()+"Alerta, temperatura sub 20!"

        }catch (e:Exception){
            return "Eroare check temp"+ e.printStackTrace()
        }
    }
}