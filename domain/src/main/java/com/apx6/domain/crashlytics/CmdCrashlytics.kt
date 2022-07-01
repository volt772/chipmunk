package com.apx6.domain.crashlytics

import com.apx6.domain.exception.MpdOperationException


interface CmdCrashlytics {

    fun collectionEnabled(enabled: Boolean)

    fun setEmail(emailAddress: String? = null)

    fun recordLog(str: String)

    fun recordException(t: Throwable)

    fun recordException(t: MpdOperationException)

}