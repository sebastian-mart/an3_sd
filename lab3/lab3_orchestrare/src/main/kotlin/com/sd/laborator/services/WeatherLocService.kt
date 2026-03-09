package com.sd.laborator.services

import com.sd.laborator.interfaces.CheckBlacklistInterface
import com.sd.laborator.interfaces.CheckTempInterface
import com.sd.laborator.interfaces.TimeServiceInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.interfaces.WeatherLocInterface
import com.sd.laborator.pojo.CityInfoData
import com.sd.laborator.pojo.WeatherForecastData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WeatherLocService : WeatherLocInterface {
    @Autowired
    private lateinit var checkBlacklistService: CheckBlacklistInterface
    @Autowired
    private lateinit var locationSearchService: LocationSearchService
    @Autowired
    private lateinit var timeService: TimeServiceInterface
    @Autowired
    private lateinit var weatherForecastService: WeatherForecastInterface
    @Autowired
    private lateinit var checkTempService: CheckTempInterface
    override fun getFullReport(location: String): String {
        val valid = checkBlacklistService.isZoneAllowed()
        if(!checkBlacklistService.isZoneAllowed())
            return "You are not allowed to access the info!"
        // se incearca preluarea WOEID-ului locaţiei primite in URL
        val cityInfo = locationSearchService.getLocationId(location)
// dacă locaţia nu a fost găsită, răspunsul va fi corespunzător
        if ((cityInfo !is CityInfoData) || cityInfo == CityInfoData(-1.0,-1.0,"invalid")) {
            return "Nu s-au putut gasi date meteo pentru cuvintele cheie\"$location\"!"
        }
// pe baza ID-ului de locaţie, se interoghează al doilea serviciu care returnează datele meteo
// încapsulate într-un obiect POJO
        val rawForecastData: WeatherForecastData =
            weatherForecastService.getForecastData(cityInfo)
        rawForecastData.date=timeService.getCurrentTime()

        val alarm = "Alarma! Temperatura sub 20"
        return if(!checkTempService.isValidTemp(rawForecastData))
            rawForecastData.toString()+alarm
        else rawForecastData.toString()
    }

}