package com.cheesy.otapara.otaparaclient.repository

import android.annotation.SuppressLint
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_JWT_TOKEN = "jwt_token"
private const val KEY_JWT_REFRESH_TOKEN = "jwt_refresh_token"
@Singleton
class SettingsRepository @Inject constructor(
    private val preferences: SharedPreferences
) {
    fun getJWTToken():String{
       return preferences.getString(KEY_JWT_TOKEN, "") ?: ""
    }

    fun setJWTToken(token:String){
        preferences.edit().putString(KEY_JWT_TOKEN, token).apply()
    }

    fun getJWTRefreshToken():String{
       return preferences.getString(KEY_JWT_REFRESH_TOKEN, "") ?: ""
    }

    fun setJWTRefreshToken(token:String){
        preferences.edit().putString(KEY_JWT_REFRESH_TOKEN, token).apply()
    }
}