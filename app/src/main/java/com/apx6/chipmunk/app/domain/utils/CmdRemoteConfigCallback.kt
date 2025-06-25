package com.apx6.chipmunk.app.domain.utils

interface CmdRemoteConfigCallback {

    fun success(
        newVersionExists: Boolean
    )

    fun fail()
}
