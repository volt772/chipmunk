package com.mailplug.albatross.app.di.network

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object PushConnectionModule {
//
//    @Singleton
//    @Provides
//    @PushConnectionUrl
//    fun providePushConnectionBaseUrl() = MpConstants.Uri.PUSH_CONNECTION
//
//    @Provides
//    @Singleton
//    @PushConnectionService
//    fun providePushConnectionRetrofitClient(
//        @PushConnectionUrl baseUrl: String,
//        @PushConnectionHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
//    ): Retrofit = retrofitPushConnectionBuilder(baseUrl, converterFactory, okHttpClient)
//
//    @Singleton
//    @Provides
//    fun providePushConnectionApiService(
//        @PushConnectionService retrofit: Retrofit
//    ): PushConnectionApiService = retrofit.create(PushConnectionApiService::class.java)
//
//    private fun retrofitPushConnectionBuilder(
//        baseUrl: String,
//        converterFactory: Converter.Factory,
//        okHttpClient: OkHttpClient
//    ) = Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .addConverterFactory(converterFactory)
//        .client(okHttpClient)
//        .build()
//
}