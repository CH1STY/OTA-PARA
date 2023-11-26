package com.cheesy.otapara.otaparaclient.data.api

import com.cheesy.otapara.otaparaclient.data.model.MalTokenAuthResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //Some Retrofit Actions Will Be Here
    @GET(ApiConstants.ENDPOINT_FETCH_MAL_TOKEN)
    suspend fun getAuthToken(@Query("code") code: String): Response<MalTokenAuthResponse>

}