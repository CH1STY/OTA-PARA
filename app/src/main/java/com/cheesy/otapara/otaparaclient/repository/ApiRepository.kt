package com.cheesy.otapara.otaparaclient.repository

import android.util.Log
import com.cheesy.otapara.otaparaclient.data.api.ApiService
import com.cheesy.otapara.otaparaclient.data.model.MalTokenAuthResponse
import com.cheesy.otapara.otaparaclient.util.custom_exception.ApiRequestFailedException

private const val TAG = "ApiRepository"
class ApiRepository(private val apiService: ApiService) {
    suspend fun getMalToken(code: String): MalTokenAuthResponse {
        val response = apiService.getAuthToken(code)
        if (response.isSuccessful && response.body() != null) {
            Log.i(TAG, "getMalToken: jwt: ${response.body()!!.token}")
            Log.i(TAG, "refreshToken : ${response.body()!!.refreshToken}")
            return response.body()!!
        } else {
            throw ApiRequestFailedException("Failed to Fetch Token from API")
        }
    }
}