package com.apx6.data.di.network

import com.apx6.data.di.*
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


private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 10L
private const val READ_TIMEOUT = 10L

private const val KAKAO_MAP_CONNECT_TIMEOUT = 10L
private const val KAKAO_MAP_WRITE_TIMEOUT = 10L
private const val KAKAO_MAP_READ_TIMEOUT = 10L

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    /**
     * @desc 앱 HttpClient
     */
    @Provides
    @Singleton
    @ChipmunkHttpClient
    fun provideChipmunkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @ChipmunkInterceptor chipmunkInterceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(chipmunkInterceptor)
            addInterceptor(loggingInterceptor)
        }.build()
    }

    /**
     * @desc Kakao Map HttpClient
     */
    @Provides
    @Singleton
    @KakaoMapHttpClient
    fun provideKakaoMapHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @KakaoMapInterceptor kakaoMapInterceptor: Interceptor,
    ): OkHttpClient {

        return OkHttpClient.Builder().apply {
            connectTimeout(KAKAO_MAP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(KAKAO_MAP_WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(KAKAO_MAP_READ_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(kakaoMapInterceptor)
            addInterceptor(loggingInterceptor)
        }.build()
    }
}
