package com.sd.laborator.services

import com.sd.laborator.interfaces.TimeServiceInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.CityInfoData
import com.sd.laborator.pojo.WeatherForecastData
import com.sd.laborator.pojo.WeatherState
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.URL
import kotlin.math.roundToInt

@Service
class WeatherForecastService : WeatherForecastInterface {
    //adaugata injectare dependinta + interfata pentru respectarea dependentei inverse
    @Autowired
    private lateinit var timeService: TimeServiceInterface

    override fun getForecastData(info: CityInfoData): WeatherForecastData {
        val forecastDataURL =
            URL("https://api.open-meteo.com/v1/forecast?latitude=${info.lat}&longitude=${info.lon}&current=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m,wind_direction_10m&daily=temperature_2m_max,temperature_2m_min&timezone=auto ")
// preluare conţinut răspuns HTTP la o cerere GET către URL-ul de mai sus
        val rawResponse: String = forecastDataURL.readText()
// parsare obiect JSON primit
        val responseRootObject = JSONObject(rawResponse)
        val weatherDataObject =
            responseRootObject.getJSONObject("current")
        val tempMaxDataObject = responseRootObject.getJSONObject("daily").getJSONArray("temperature_2m_max")
        val tempMinDataObject = responseRootObject.getJSONObject("daily").getJSONArray("temperature_2m_min")

// construire şi returnare obiect POJO care încapsulează datele meteo
        return WeatherForecastData(
            location = info.name.toString(),
            date = "",
            weatherState = WeatherState.fromCode(weatherDataObject.getInt("weather_code")),
            windDirection =
                weatherDataObject.getInt("wind_direction_10m").toString() + "degrees",
            windSpeed =
                weatherDataObject.getFloat("wind_speed_10m").roundToInt(),
            minTemp = tempMinDataObject.getFloat(0).roundToInt(),
            maxTemp = tempMaxDataObject.getFloat(0).roundToInt(),
            currentTemp =
                weatherDataObject.getFloat("temperature_2m").roundToInt(),
            humidity = weatherDataObject.getFloat("relative_humidity_2m").roundToInt()
        )
    }
}