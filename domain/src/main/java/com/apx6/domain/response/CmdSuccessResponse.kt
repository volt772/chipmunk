package com.apx6.domain.response

/**
 * @desc Response 성공 객체
 */

data class CmdSuccessResponse<T>(

    val code : Int,

    val body : T?,

    val nextOffset : Int?= null,

    val limit : Int?= null

)