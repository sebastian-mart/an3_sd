package com.sd.laborator

import org.json.JSONObject
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale

//@SpringBootApplication
//class Hello

fun isZoneAllowed(): Boolean {
    val file = java.io.File("blacklist")
    return if(!file.exists() && file.readText().contains(getCurrentSystemZone(),ignoreCase = true))
        false
    else
        true
}
fun getCurrentSystemZone(): String{
    return Locale.getDefault().country
}

fun main(args: Array<String>) {
//    runApplication<Hello>(*args)
    print(isZoneAllowed())
}