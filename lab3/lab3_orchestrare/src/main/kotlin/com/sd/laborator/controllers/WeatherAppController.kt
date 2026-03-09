package com.sd.laborator.controllers
import com.sd.laborator.interfaces.CheckBlacklistInterface
import com.sd.laborator.interfaces.CheckTempInterface
import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.interfaces.WeatherLocInterface
import com.sd.laborator.pojo.CityInfoData
import com.sd.laborator.pojo.WeatherForecastData
import com.sd.laborator.services.CheckTempService
import com.sd.laborator.services.TimeService
import com.sd.laborator.services.WeatherLocService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WeatherAppController {

    @Autowired
    private lateinit var weatherLocService: WeatherLocInterface


    @RequestMapping("/getforecast/{location}", method =
        [RequestMethod.GET])


    @ResponseBody
    fun getForecast(@PathVariable location: String): String {
        return weatherLocService.getFullReport(location)
    }

}