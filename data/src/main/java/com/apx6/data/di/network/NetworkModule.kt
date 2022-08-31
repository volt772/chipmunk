package com.apx6.data.di.network

import com.apx6.data.di.*
import com.apx6.data.network.ChipmunkApiService
import com.apx6.data.network.KakaoMapApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @ChipmunkBaseUrl
    fun provideChipmunkBaseUrl() = "https://apx-prooya.co.kr"

    @Singleton
    @Provides
    @KakaoMapBaseUrl
    fun provideKakaoMapBaseUrl() = "https://dapi.kakao.com"

    /**
     * @Desc Retrofit Clients
     */
    @Provides
    @Singleton
    @ChipmunkService
    fun provideChipmunkRetrofitClient(
        @ChipmunkBaseUrl baseUrl: String,
        @ChipmunkHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
    ): Retrofit {
        return retrofitBuilder(baseUrl, okHttpClient)
    }

    @Provides
    @Singleton
    @KakaoMapService
    fun provideKakaoMapRetrofitClient(
        @KakaoMapBaseUrl baseUrl: String,
        @KakaoMapHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
    ): Retrofit {
        return retrofitBuilder(baseUrl, okHttpClient)
    }

    /**
     * @Desc API Services
     */
    @Singleton
    @Provides
    fun provideChipmunkApiService(
        @ChipmunkService retrofit: Retrofit
    ): ChipmunkApiService = retrofit.create(ChipmunkApiService::class.java)

    @Singleton
    @Provides
    fun provideKakaoMapApiService(
        @KakaoMapService retrofit: Retrofit
    ): KakaoMapApiService = retrofit.create(KakaoMapApiService::class.java)

    /**
     * @Desc Retrofit Builder
     */
    private fun retrofitBuilder(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
}