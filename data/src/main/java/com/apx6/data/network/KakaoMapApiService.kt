package com.apx6.data.network

import com.apx6.data.network.ApiServiceUris.URL_MAP_LOCATION
import com.apx6.domain.constants.CmdNetworkKeyTags
import com.apx6.domain.dto.CmdLocation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface KakaoMapApiService {

    @GET(URL_MAP_LOCATION)
    suspend fun getLocationDocs(
        @Query(CmdNetworkKeyTags.QueryParam.QUERY) query: String?= "",
    ): Response<CmdLocation>

}