package com.apx6.domain.dto

data class CmdAttachment (

    val id: Int = 0,

    val tid: Int,

    val name: String,

    val size: Int,

    val contentType: String,

    val createdTime: Long

)
