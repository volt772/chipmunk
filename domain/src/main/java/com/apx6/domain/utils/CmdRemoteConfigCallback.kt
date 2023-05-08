package com.apx6.domain.utils

interface CmdRemoteConfigCallback {

    fun success(
        newVersionExists: Boolean
    )

    fun fail()
}
