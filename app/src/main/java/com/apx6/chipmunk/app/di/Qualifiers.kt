package com.apx6.chipmunk.app.di

import javax.inject.Qualifier

/**
 * @Related
 * NetworkModule
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TaskBaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CrmBaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PushConnectionUrl

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