package com.apx6.data.di.network

import com.apx6.data.di.ChipmunkInterceptor
import com.apx6.data.di.KakaoMapInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


/**
 * InterceptorModule
 */

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * @desc Chipmunk Interceptor
     */
    @Provides
    @Singleton
    @ChipmunkInterceptor
    fun provideChipmunkInterceptor(): Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request())
        }
    }

    /**
     * @desc Kakao Map Interceptor
     */
    @Provides
    @Singleton
    @KakaoMapInterceptor
    fun provideKakaoMapInterceptor(): Interceptor {
        return Interceptor { chain ->
            val apiKey = "2b82712949a4f348da1dda55f11619bc"
            val builder = chain.request().newBuilder()
                .header("Authorization", "KakaoAK $apiKey")
            chain.proceed(builder.build())
        }
    }
}
