package com.apx6.domain.utils

import com.apx6.domain.dto.CmdAppUpdateValue
import com.apx6.domain.dto.CmdRemoteConfigValue

interface CmdRemoteConfigMgr {

    fun getCurrentVersionName(): String

    fun syncVersionConfigValue(
        callback: CmdRemoteConfigCallback
    )

    fun versionDetails(): CmdAppUpdateValue

}