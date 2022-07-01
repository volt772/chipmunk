package com.apx6.domain.response

import com.apx6.domain.constants.CmdNetworkKey
import com.google.gson.annotations.SerializedName

/**
 * @desc Response 리스트 반환
 */

data class CmdResponseListDto<T> (

    @field:SerializedName(CmdNetworkKey.Response.VALUE)
    val value: List<T>,

    @field:SerializedName(CmdNetworkKey.Response.COUNT)
    var count: Int = 0,

    @field:SerializedName(CmdNetworkKey.Response.OFFSET)
    var offset: Int = 0,

    @field:SerializedName(CmdNetworkKey.Response.LIMIT)
    var limit: Int = 0,

    @field:SerializedName(CmdNetworkKey.Response.TOTAL)
    var total: Int = 0,

)