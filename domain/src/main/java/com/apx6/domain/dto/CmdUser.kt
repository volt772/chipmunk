package com.apx6.domain.dto

data class CmdUser (

    val account: String,

    val nickName: String,

    val email: String?= "",

    val regDate: Long,

    val profileThumbnail: String?= "",

    val fToken: String

)
