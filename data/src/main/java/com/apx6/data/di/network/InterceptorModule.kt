package com.apx6.data.di.network

import com.apx6.data.di.GeneralInterceptor
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

    @Provides
    @Singleton
    @GeneralInterceptor
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->

            val builder = chain.request().newBuilder()
//                    .header(AUTHORIZATION, bearer)
//                    .header(USER_AGENT, userAgent?: "")
//                    .header(USER_LANGUAGE, appLang?: "")
//                    .header(ACCEPT, "application/json")
            chain.proceed(builder.build())


//            if (accessToken.isNotBlank()) {
////                val userAgent = mpPreference.getString(MpConstants.Prefs.USER_AGENT, "")
////                val appLang = mpPreference.getString(MpConstants.Prefs.APP_LANGUAGE, "")
////                val bearer = "$BEARER $accessToken"
//                val builder = chain.request().newBuilder()
////                    .header(AUTHORIZATION, bearer)
////                    .header(USER_AGENT, userAgent?: "")
////                    .header(USER_LANGUAGE, appLang?: "")
////                    .header(ACCEPT, "application/json")
//                chain.proceed(builder.build())
//            } else {
//                chain.proceed(chain.request())
//            }
        }
    }
//
//    /**
//     * @desc 첨부파일 전용 TASK Interceptor
//     */
//    @Provides
//    @Singleton
//    @ProgressInterceptor
//    fun provideProgressInterceptor(listener: ProgressListener): Interceptor {
//        return Interceptor { chain ->
//            val originalResponse = chain.proceed(chain.request())
//            val body: ResponseBody = originalResponse.body ?: throw IOException("empty response")
//            val progressResponseBody = ProgressResponseBody(body, listener)
//
//            originalResponse.newBuilder()
//                .body(progressResponseBody)
//                .build()
//        }
//    }
}
