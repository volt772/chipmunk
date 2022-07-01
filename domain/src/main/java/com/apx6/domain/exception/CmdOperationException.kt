package com.apx6.domain.exception


class MpdOperationException(

    val errorCode: Int,

    message: String? = null

): Exception(" ${message ?: ""} ")

/**
 * not null 임을 보장한다.
 * [value] 가 null 이면 [MpdOperationException] 을 발생시킨다.
 */
fun <T : Any> notNullRequired(
    value: T?,
    errorCode: Int= 0,
    message: String? = null
): T {
    return value ?: throw MpdOperationException(errorCode, message)
}