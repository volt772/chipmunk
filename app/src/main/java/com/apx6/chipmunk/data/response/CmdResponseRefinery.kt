package com.apx6.chipmunk.data.response

import retrofit2.Response


interface CmdResponseRefinery {

    fun <T> response(response: Response<T>) : CmdResult<Any>

}