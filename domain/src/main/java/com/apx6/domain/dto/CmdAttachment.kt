package com.apx6.domain.dto

data class CmdAttachment (

    val id: Int = 0,

    val clId: Int,

    val name: String,

    val size: Int,

    val contentType: String,

    val createdTime: Long

)
