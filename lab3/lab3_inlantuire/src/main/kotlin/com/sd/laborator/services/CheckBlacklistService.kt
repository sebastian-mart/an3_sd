package com.sd.laborator.services

import com.sd.laborator.interfaces.CheckBlacklistInterface
import org.springframework.stereotype.Service
import java.util.Locale
import java.util.Locale.IsoCountryCode

@Service
class CheckBlacklistService : CheckBlacklistInterface {
    override fun isZoneAllowed(): String {
        val file = java.io.File("blacklist")
        return if(file.exists() && file.readText().contains(getCurrentSystemZone(),ignoreCase = true))
            false
        else
            true
    }
    private fun getCurrentSystemZone(): String{
        return Locale.getDefault().country
    }
}