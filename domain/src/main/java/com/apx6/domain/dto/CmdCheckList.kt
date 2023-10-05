package com.apx6.domain.dto

data class CmdCheckList (

    val id: Int = 0,

    val cid: Int,

    val uid: Int,

    val title: String,

    val memo: String?= "",

    val exeDate: Long,

    val checkListName: String?= "",

) {

    companion object {
        fun default(): CmdCheckList {
            return CmdCheckList(
                id = 0,
                cid = -1,
                uid = -1,
                title = "",
                memo = "",
                exeDate = 0L
            )
        }
    }
}
