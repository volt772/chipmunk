package com.apx6.data.network

import com.apx6.data.network.ApiServiceUris.URL_ACCOUNT_PROFILE
import retrofit2.Response
import retrofit2.http.GET


interface ChipmunkApiService {

    @GET(URL_ACCOUNT_PROFILE)
    suspend fun getAccountProfile(): Response<Unit>

}