package com.apx6.domain.response

import com.apx6.domain.exception.CmdErrorDto

/**
 * @desc Response 실패 객체
 */

data class CmdErrorResponse(

    var error: CmdErrorDto? = null

)