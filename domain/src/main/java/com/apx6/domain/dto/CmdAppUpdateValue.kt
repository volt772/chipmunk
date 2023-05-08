package com.apx6.domain.dto

data class CmdAppUpdateValue (

    /* 현재 Gradle 버전코드*/
    val currAppVersionCode: Int,

    /* 현재 Gradle 버전명*/
    val currAppVersionName: String,

    /* 업데이트 버전코드*/
    val remoteAppVersionCode: Int,

    /* 업데이트 버전명*/
    val remoteAppVersionName: String,

    /* 업데이트 설명*/
    val remoteDescription: String

) {
    companion object {
        fun default(): CmdAppUpdateValue {
            return CmdAppUpdateValue(
                currAppVersionCode = 0,
                currAppVersionName = "",
                remoteAppVersionCode = 0,
                remoteAppVersionName = "",
                remoteDescription = "",
            )
        }
    }
}