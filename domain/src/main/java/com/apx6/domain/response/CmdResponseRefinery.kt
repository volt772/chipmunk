package com.apx6.domain.response

import retrofit2.Response


interface CmdResponseRefinery {

    fun <T> response(response: Response<T>) : Any

}