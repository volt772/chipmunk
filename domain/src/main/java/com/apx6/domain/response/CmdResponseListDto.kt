package com.apx6.domain.response

import com.apx6.domain.constants.CmdNetworkKeyTags
import com.google.gson.annotations.SerializedName

/**
 * @desc Response 리스트 반환
 */

data class CmdResponseListDto<T> (

    @field:SerializedName(CmdNetworkKeyTags.Response.VALUE)
    val value: List<T>,

    @field:SerializedName(CmdNetworkKeyTags.Response.COUNT)
    var count: Int = 0,

    @field:SerializedName(CmdNetworkKeyTags.Response.OFFSET)
    var offset: Int = 0,

    @field:SerializedName(CmdNetworkKeyTags.Response.LIMIT)
    var limit: Int = 0,

    @field:SerializedName(CmdNetworkKeyTags.Response.TOTAL)
    var total: Int = 0,

)