package com.apx6.data.response

import com.apx6.domain.response.CmdErrorResponse
import com.apx6.domain.response.CmdResponseRefinery
import com.apx6.domain.response.CmdSuccessResponse
import retrofit2.Response
import javax.inject.Inject


class CmdResponseRefineryImpl @Inject constructor(
): CmdResponseRefinery {

    override fun <T> response(response: Response<T>) = responseByCode(response)

    /**
     * Response Return
     * @desc Define from Response Code
     */
    private fun <T> responseByCode(response: Response<T>): Any {
        return if (response.isSuccessful) {
            respSuccess(response)
        } else {
            respFail(response)
        }
    }

    private fun <T> respSuccess(
        response: Response<T>
    ): CmdSuccessResponse<T> {
        return CmdSuccessResponse(code = response.code(), body = response.body())
    }

    private fun <T> respFail(
        response: Response<T>
    ): CmdErrorResponse {

        return CmdErrorResponse(message = response.message())
    }
}