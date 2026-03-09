package com.sd.laborator.services

import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.pojo.CityInfoData
import org.json.JSONObject
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
@Primary//daca ai doua sau mai multe servicii ce implementeaza aceeasi interfata
//atunci cand vrei ca una din clase sa fie folosita primar folosesti primary
class LocationSearchService : LocationSearchInterface {
    override fun getLocationId(locationName: String): CityInfoData {
// codificare parametru URL (deoarece poate conţine caractere speciale)
        val encodedLocationName = URLEncoder.encode(locationName, StandardCharsets.UTF_8.toString())
// construire obiect de tip URL
        val locationSearchURL =
            URL("https://geocoding-api.open-meteo.com/v1/search?name=$encodedLocationName&count=1")
// preluare raspuns HTTP (se face cerere GET şi se preia conţinutul răspunsului sub formă de text)
        val rawResponse: String = locationSearchURL.readText()
// parsare obiect JSON
        //OBLIGATORIU TREBUIE SA AVEM SI OBIECT ROOT: IN CAZUL ASTA {data:}
        val responseRootObject = JSONObject("{\"data\": ${rawResponse}}")
        //takeUnless: pastreaza obiect daca nu respecta conditia din paranteze
        val responseContentObject =
            responseRootObject.getJSONObject("data").takeUnless { it.isEmpty }?.getJSONArray("results")?.getJSONObject(0)
        return responseContentObject?.let {
            CityInfoData(it.getDouble("longitude"),
                it.getDouble("latitude"),
                it.getString("name"))
        } ?: CityInfoData(-1.0,-1.0,"invalid")
    }
}
