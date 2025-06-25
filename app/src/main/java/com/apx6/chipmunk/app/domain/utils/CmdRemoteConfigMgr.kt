package com.apx6.chipmunk.app.domain.utils

import com.apx6.chipmunk.app.domain.dto.CmdAppUpdateValue

interface CmdRemoteConfigMgr {

    fun getCurrentVersionName(): String

    fun syncVersionConfigValue(
        callback: CmdRemoteConfigCallback
    )

    fun versionDetails(): CmdAppUpdateValue

}