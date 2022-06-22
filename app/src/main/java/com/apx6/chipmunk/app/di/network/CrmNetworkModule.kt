package com.apx6.chipmunk.app.di.network

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * CrmNetworkModule
 */

@Module
@InstallIn(SingletonComponent::class)
object CrmNetworkModule {
//
//    @Singleton
//    @Provides
//    @CrmBaseUrl
//    fun provideCrmBaseUrl() = MpConstants.Uri.CRM
//
//    @Provides
//    @Singleton
//    @CrmService
//    fun provideCrmRetrofitClient(
//        @CrmBaseUrl baseUrl: String,
//        @CrmHttpClient okHttpClient: OkHttpClient
//    ): Retrofit {
//        return retrofitCrmBuilder(baseUrl, okHttpClient)
//    }
//
//    @Singleton
//    @Provides
//    fun provideCrmApiService(
//        @CrmService retrofit: Retrofit
//    ): CrmApiService = retrofit.create(CrmApiService::class.java)
//
//    private fun retrofitCrmBuilder(
//        baseUrl: String,
//        okHttpClient: OkHttpClient
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(okHttpClient)
//            .build()
//    }

}