package com.apx6.data.network

import com.apx6.data.network.ApiServiceUris.URL_ACCOUNT_PROFILE
import com.apx6.domain.dto.CmdLocation
import retrofit2.Response
import retrofit2.http.GET


interface KakaoMapApiService {

    /* 프로필 조회*/
    @GET(URL_ACCOUNT_PROFILE)
    suspend fun getAccountProfile(): Response<CmdLocation>

}