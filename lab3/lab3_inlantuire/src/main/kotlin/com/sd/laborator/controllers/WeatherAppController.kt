package com.sd.laborator.controllers
import com.sd.laborator.interfaces.CheckBlacklistInterface
import com.sd.laborator.interfaces.CheckTempInterface
import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.CityInfoData
import com.sd.laborator.pojo.WeatherForecastData
import com.sd.laborator.services.CheckTempService
import com.sd.laborator.services.TimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WeatherAppController {

    //autowired folosit pentru injectare dependinte
    //Observaţi că aceste 2 proprietăţi nu sunt instanţiate nicăieri în clasă, ci sunt doar declarate.
    //Iniţializarea este făcută automat de Spring. De aceea a fost nevoie de specificatorul lateinit,
    //deoarece Kotlin nu permite declararea unei proprietăţi fără iniţializarea acesteia în constructor sau
    //imediat după declaraţie.
//    @Autowired
//    private lateinit var locationSearchService: LocationSearchInterface
//    @Autowired
//    private lateinit var weatherForecastService: WeatherForecastInterface
    @Autowired
    private lateinit var checkBlacklistService: CheckBlacklistInterface
//    @Autowired
//    private lateinit var checkTempService: CheckTempInterface

    @RequestMapping("/getforecast/{location}", method =
        [RequestMethod.GET])


    @ResponseBody
    fun getForecast(@PathVariable location: String): String {
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
        val alarm = "Alarma! Temperatura sub 20"
        return if(!checkTempService.isValidTemp(rawForecastData))
            rawForecastData.toString()+alarm
        else rawForecastData.toString()
    }

}