package com.apx6.chipmunk.app.di.network

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * InterceptorModule
 */

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

//    @Provides
//    @Singleton
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    }
//
//    /**
//     * @desc 일반 TASK Interceptor
//     */
//    @Provides
//    @Singleton
//    @GeneralInterceptor
//    fun provideInterceptor(mpPreference: MpPreference): Interceptor {
//        return Interceptor { chain ->
//            val accessToken = try {
//                MpAuthManager.getInstance().acquireAccessToken(mpPreference.getString(MpConstants.Prefs.USER_EMAIL, "") ?: "")
//            } catch (e: Exception) {
//                ""
//            }
//            if (accessToken.isNotBlank()) {
//                val userAgent = mpPreference.getString(MpConstants.Prefs.USER_AGENT, "")
//                val appLang = mpPreference.getString(MpConstants.Prefs.APP_LANGUAGE, "")
//                val bearer = "$BEARER $accessToken"
//                val builder = chain.request().newBuilder()
//                    .header(AUTHORIZATION, bearer)
//                    .header(USER_AGENT, userAgent?: "")
//                    .header(USER_LANGUAGE, appLang?: "")
//                    .header(ACCEPT, "application/json")
//                chain.proceed(builder.build())
//            } else {
//                chain.proceed(chain.request())
//            }
//        }
//    }
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
//
//    /**
//     * @desc CRM Interceptor
//     */
//    @Provides
//    @Singleton
//    @CrmInterceptor
//    fun provideCrmInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            chain.proceed(chain.request())
//        }
//    }
//
//    /**
//     * @desc Push Connection Interceptor
//     */
//    @Provides
//    @Singleton
//    @PushConnectionInterceptor
//    fun providePushConnectionInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            chain.proceed(chain.request())
//        }
//    }
}
