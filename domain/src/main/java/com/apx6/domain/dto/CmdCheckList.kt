package com.apx6.domain.dto

data class CmdCheckList (

    val id: Int = 0,

    val cid: Int,

    val uid: Int,

    val title: String,

    val memo: String?= "",

    val startDate: Long,

    val endDate: Long

) {

    companion object {
        fun default(): CmdCheckList {
            return CmdCheckList(
                id = 0,
                cid = -1,
                uid = -1,
                title = "",
                memo = "",
                startDate = 0L,
                endDate = 0L,
            )
        }
    }
}
