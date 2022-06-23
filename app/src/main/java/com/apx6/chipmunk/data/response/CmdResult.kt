package com.apx6.chipmunk.data.response

import com.apx6.chipmunk.domain.response.CmdErrorResponse
import com.apx6.chipmunk.domain.response.CmdSuccessResponse


sealed class CmdResult<out T: Any> {

    data class Success<out T: Any>(val data: CmdSuccessResponse<*>): CmdResult<T>()

    data class Error<out T: Any>(val exception: CmdErrorResponse): CmdResult<T>()

//    object InProgress: MpdResult<Nothing>()

}