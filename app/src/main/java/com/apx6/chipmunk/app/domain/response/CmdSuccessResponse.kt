package com.apx6.chipmunk.app.domain.response

/**
 * @desc Response 성공 객체
 */

data class CmdSuccessResponse<T>(

    val code : Int,

    val body : T?

)