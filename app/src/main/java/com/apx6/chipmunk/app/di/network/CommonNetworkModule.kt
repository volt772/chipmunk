package com.apx6.chipmunk.app.di.network

import com.apx6.chipmunk.app.di.*
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

private const val ATTACH_CONNECT_TIMEOUT = 30L
private const val ATTACH_WRITE_TIMEOUT = 30L
private const val ATTACH_READ_TIMEOUT = 30L

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

    /**
     * @desc 첨부파일 전용 TASK HttpClient
     */
    @Provides
    @Singleton
    @ProgressHttpClient
    fun provideProgressHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @GeneralInterceptor generalInterceptor: Interceptor,
        @ProgressInterceptor progressInterceptor: Interceptor,
    ): OkHttpClient {

        return OkHttpClient.Builder().apply {
            connectTimeout(ATTACH_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(ATTACH_WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(ATTACH_READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(generalInterceptor)
            addInterceptor(progressInterceptor)
            addInterceptor(loggingInterceptor)
        }.build()
    }

    /**
     * @desc CRM 전용 TASK HttpClient
     */
    @Provides
    @Singleton
    @CrmHttpClient
    fun provideCrmHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @CrmInterceptor crmInterceptor: Interceptor,
    ): OkHttpClient {

        return OkHttpClient.Builder().apply {
            connectTimeout(ATTACH_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(ATTACH_WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(ATTACH_READ_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(crmInterceptor)
            addInterceptor(loggingInterceptor)
        }.build()
    }

    /**
     * @desc Push Connection 전용 HttpClient
     */
    @Provides
    @Singleton
    @PushConnectionHttpClient
    fun providePushConnectionHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @PushConnectionInterceptor pushConnectionInterceptor: Interceptor,
    ): OkHttpClient {

        return OkHttpClient.Builder().apply {
            connectTimeout(ATTACH_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(ATTACH_WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(ATTACH_READ_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(pushConnectionInterceptor)
            addInterceptor(loggingInterceptor)
        }.build()
    }

}