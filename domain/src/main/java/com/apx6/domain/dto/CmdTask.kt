package com.apx6.domain.dto

data class CmdTask (

    val id: Int = 0,

    val cid: Int,

    val uid: Int,

    val title: String,

    val memo: String?= "",

    val startDate: Long,

    val endDate: Long

)
