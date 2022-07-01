package com.apx6.data.di.network

import com.apx6.data.di.GeneralHttpClient
import com.apx6.data.di.GeneralInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * CommonNetworkModule
 */

private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 10L
private const val READ_TIMEOUT = 10L

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    /**
     * @desc 일반 TASK HttpClient
     */
    @Provides
    @Singleton
    @GeneralHttpClient
    fun provideGeneralHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @GeneralInterceptor generalInterceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(generalInterceptor)
            addInterceptor(loggingInterceptor)
        }.build()
    }

}