package com.apx6.data.network

import com.apx6.data.network.ApiServiceUris.URL_DUMMY_CHIPMUNK
import retrofit2.Response
import retrofit2.http.GET


interface ChipmunkApiService {

    @GET(URL_DUMMY_CHIPMUNK)
    suspend fun dummy(): Response<Unit>

}