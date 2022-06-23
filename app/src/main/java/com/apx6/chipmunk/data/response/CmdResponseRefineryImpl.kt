package com.apx6.chipmunk.data.response

import com.apx6.chipmunk.app.crashlytics.CmCrashlytics
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.apx6.chipmunk.domain.exception.CmdErrorDto
import com.apx6.chipmunk.domain.response.CmdErrorResponse
import com.apx6.chipmunk.domain.response.CmdSuccessResponse
import com.apx6.chipmunk.domain.response.CmdResponseListDto
import retrofit2.Response
import javax.inject.Inject


class CmdResponseRefineryImpl @Inject constructor(
    private val crashlytics: CmCrashlytics,
): CmdResponseRefinery {

    override fun <T> response(response: Response<T>) = responseByCode(response)

    /**
     * Response Return
     * @desc Define from Response Code
     */
    private fun <T> responseByCode(response: Response<T>): CmdResult<Any> {
        return when(response.code()) {
            in 200..299 -> response2xCode(response)
            in 300..399 -> response3xCode(response)
            in 400..499 -> response4xCode(response)
            in 500..599 -> response5xCode(response)
            else -> responseUnexpectedException(response = response, exception = "")
        }
    }

    /**
     * Success
     * @desc 2x
     */
    private fun <T> response2xCode(
        response: Response<T>
    ): CmdResult<CmdSuccessResponse<T>> {

        val (nextOffset, limit) = if (response.body() is CmdResponseListDto<*>) {
            val body = response.body() as CmdResponseListDto<*>
            if (body.value.isNotEmpty()) {
                if (body.count == body.limit) { body.offset + 1 } else { null }
            } else { null } to body.limit
        } else {
            null to null
        }

        return CmdResult.Success(
            CmdSuccessResponse(
                code = response.code(),
                body = response.body(),
                nextOffset = nextOffset,
                limit = limit
            )
        )
    }

    /**
     * Redirect
     * @desc 3x
     */
    private fun <T> response3xCode(
        response: Response<T>
    ): CmdResult<CmdErrorResponse> {
        return responseUnexpectedException(response = response, exception = "")
    }

    /**
     * Failed
     * @desc 4x
     */
    private fun <T> response4xCode(
        response: Response<T>
    ): CmdResult<CmdErrorResponse> {
        return try {
            val gson = Gson()
            val type = object : TypeToken<CmdErrorResponse>() {}.type
            val errorResponse: CmdErrorResponse = gson.fromJson(response.errorBody()?.charStream(), type)

            CmdResult.Error(CmdErrorResponse(error = errorResponse.error))
        } catch (e: NullPointerException) {
            crashlytics.recordException(e)
            responseUnexpectedException(response = response, exception = e.message.toString())
        } catch (e: JsonSyntaxException) {
            crashlytics.recordException(e)
            responseUnexpectedException(response = response, exception = e.message.toString())
        } catch (e: Exception) {
            crashlytics.recordException(e)
            responseUnexpectedException(response = response, exception = e.message.toString())
        }
    }

    /**
     * Failed
     * @desc 5x
     */
    private fun <T> response5xCode(
        response: Response<T>
    ): CmdResult<CmdErrorResponse> {
        crashlytics.recordLog("[Chipmunk] Exception : Response 500 Error")
        return responseUnexpectedException(response = response, exception = "")
    }

    /**
     * UnexpectedException
     * @desc ELSE
     */
    private fun <T> responseUnexpectedException(
        response: Response<T>,
        exception: String
    ): CmdResult<CmdErrorResponse> {
        crashlytics.recordLog("[Chipmunk] Unexpected Exception : $exception")
        return CmdResult.Error(
            CmdErrorResponse(error = CmdErrorDto(code = response.code(), message = exception))
        )
    }
}