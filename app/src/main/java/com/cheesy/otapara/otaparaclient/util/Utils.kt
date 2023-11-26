package com.cheesy.otapara.otaparaclient.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Base64

object Utils {
    //Function To Decode JWT
    fun decodeJwt(token: String): Map<String, Any?> {
        val parts = token.split(".")
        val header = Base64.getUrlDecoder().decode(parts[0]).toString(charset("UTF-8"))
        val payload = Base64.getUrlDecoder().decode(parts[1]).toString(charset("UTF-8"))

        val headerMap: Map<String, Any?> = Gson().fromJson(header, object : TypeToken<Map<String, Any?>>() {}.type)
        val payloadMap: Map<String, Any?> = Gson().fromJson(payload, object : TypeToken<Map<String, Any?>>() {}.type)

        val map: MutableMap<String, Any?> = mutableMapOf()
        map.putAll(headerMap)
        map.putAll(payloadMap)

        return map
    }
    const val APP_NAME = "OTA-PARA"
    const val TOKEN_FETCH_ERROR_MESSAGE = "Error During Login Operation Please Try Again"
}