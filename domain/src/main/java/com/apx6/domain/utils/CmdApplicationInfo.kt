package com.apx6.domain.utils

import android.content.Context

interface CmdApplicationInfo {

    fun getAndroidID(
        context: Context
    ): String

    fun getAppAgentPhrase(): String

    fun getUserAgentPhrase(): String

    fun getAppVersionName(): String

    fun getAppVersionCode(): Int
}
