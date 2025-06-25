package com.apx6.chipmunk.app.domain.crashlytics

import com.apx6.chipmunk.app.domain.exception.MpdOperationException


interface CmdCrashlytics {

    fun collectionEnabled(enabled: Boolean)

    fun setEmail(emailAddress: String? = null)

    fun recordLog(str: String)

    fun recordException(t: Throwable)

    fun recordException(t: MpdOperationException)

}