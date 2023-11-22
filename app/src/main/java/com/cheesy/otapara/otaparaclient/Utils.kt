package com.cheesy.otapara.otaparaclient

import kotlin.random.Random

object Utils {
    fun getRandomNumber(): Int {
        return Random(0).nextInt()
    }
    const val APP_NAME = "OTA-PARA"
}