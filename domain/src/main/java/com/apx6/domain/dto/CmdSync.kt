package com.apx6.domain.dto

data class CmdSync (

    val id: Int = 0,

    val uid: Int,

    val syncTime: Long,

    val syncResult: Boolean

)
