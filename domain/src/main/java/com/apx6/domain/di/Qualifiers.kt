package com.apx6.domain.di

import javax.inject.Qualifier

///**
// * @Related
// * NetworkModule
// */
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class TaskBaseUrl
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class CrmBaseUrl
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class PushConnectionUrl
//
///**
// * @Related
// * Interceptors
// */
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class GeneralInterceptor
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class ProgressInterceptor
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class CrmInterceptor
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class PushConnectionInterceptor
//
///**
// * @Related
// * HttpClients
// */
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class GeneralHttpClient
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class ProgressHttpClient
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class CrmHttpClient
//
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class PushConnectionHttpClient
//
///**
// * @Related
// * Network API Service Router
// */
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class CrmService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class AccountService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class SpaceService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class BoardService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class SectionService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class LabelService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class CardService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class LogService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class AttachService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class AttachUploadService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class AttachDownloadService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class PushConnectionService
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class BatchService
//
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