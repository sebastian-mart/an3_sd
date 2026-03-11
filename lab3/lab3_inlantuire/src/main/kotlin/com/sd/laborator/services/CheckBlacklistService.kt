package com.sd.laborator.services

import com.sd.laborator.interfaces.CheckBlacklistInterface
import com.sd.laborator.interfaces.LocationSearchInterface
import io.github.damir.denis.tudor.spring.aop.chain.annotation.ChainStep
import io.github.damir.denis.tudor.spring.aop.chain.interfaces.Chainable
import org.springframework.stereotype.Service
import java.util.Locale


@Service
@ChainStep(next = LocationSearchService::class)
//asa obtii referinta la clasa kotlin
open class CheckBlacklistService : CheckBlacklistInterface, Chainable<String, String> {
    override fun isZoneAllowed(): Boolean {
        val file = java.io.File("blacklist")
        return if(file.exists() && file.readText().contains(getCurrentSystemZone(),ignoreCase = true))
            false
        else
            true
    }
    private fun getCurrentSystemZone(): String{
        return Locale.getDefault().country
    }
    override fun proceed(input: String): String {
        return if(isZoneAllowed())
        input else "Error:You are forbidden from accesing the weather!"
    }
}