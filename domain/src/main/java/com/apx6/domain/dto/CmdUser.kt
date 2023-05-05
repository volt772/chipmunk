package com.apx6.domain.dto

data class CmdUser (

    val id: Int = 0,

    val account: String,

    val nickName: String,

    val email: String?= "",

    val regDate: Long,

    val profileThumbnail: String?= "",

    val fToken: String

) {
    companion object {
        fun default(): CmdUser {
            return CmdUser(
                id = 0,
                account = "",
                nickName = "",
                email = "",
                regDate = 0L,
                profileThumbnail = "",
                fToken = "",
            )
        }
    }
}
