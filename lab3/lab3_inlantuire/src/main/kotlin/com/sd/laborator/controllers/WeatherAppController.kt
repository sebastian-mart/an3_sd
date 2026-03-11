package com.sd.laborator.controllers
import com.sd.laborator.interfaces.CheckBlacklistInterface
import com.sd.laborator.interfaces.CheckTempInterface
import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.CityInfoData
import com.sd.laborator.pojo.WeatherForecastData
import com.sd.laborator.services.CheckBlacklistService
import com.sd.laborator.services.CheckTempService
import com.sd.laborator.services.TimeService
import io.github.damir.denis.tudor.spring.aop.chain.annotation.ChainStart
import io.github.damir.denis.tudor.spring.aop.chain.registry.ChainResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody



@Controller
open class WeatherAppController {

    //autowired folosit pentru injectare dependinte
    //Observaţi că aceste 2 proprietăţi nu sunt instanţiate nicăieri în clasă, ci sunt doar declarate.
    //Iniţializarea este făcută automat de Spring. De aceea a fost nevoie de specificatorul lateinit,
    //deoarece Kotlin nu permite declararea unei proprietăţi fără iniţializarea acesteia în constructor sau
    //imediat după declaraţie.
//    @Autowired
//    private lateinit var locationSearchService: LocationSearchInterface
//    @Autowired
//    private lateinit var weatherForecastService: WeatherForecastInterface
//    @Autowired
//    private lateinit var checkBlacklistService: CheckBlacklistInterface
//    @Autowired
//    private lateinit var checkTempService: CheckTempInterface

    @RequestMapping("/getforecast/{location}", method =
        [RequestMethod.GET])


    @ChainStart(node= CheckBlacklistService::class)
    @ResponseBody
    fun getForecast(@PathVariable location: String):ChainResult<String> = ChainResult.Pending

}