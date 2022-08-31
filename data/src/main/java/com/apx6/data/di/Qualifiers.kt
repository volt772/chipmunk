package com.apx6.data.di

import javax.inject.Qualifier

/**
 * @Related
 * NetworkModule
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChipmunkBaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KakaoMapBaseUrl

/**
 * @Related
 * Interceptors
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChipmunkInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KakaoMapInterceptor

/**
 * @Related
 * HttpClients
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChipmunkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KakaoMapHttpClient

/**
 * @Related
 * Network API Service Router
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChipmunkService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KakaoMapService

/**
 * @Related
 * Dispatchers
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

/**
 * @Related
 * Database
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
