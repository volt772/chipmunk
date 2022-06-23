package com.apx6.chipmunk.app.crashlytics

import com.apx6.chipmunk.domain.exception.MpdOperationException


interface CmCrashlytics {

    fun collectionEnabled(enabled: Boolean)

    fun setEmail(emailAddress: String? = null)

    fun recordLog(str: String)

    fun recordException(t: Throwable)

    fun recordException(t: MpdOperationException)

}