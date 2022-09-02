package com.apx6.domain.dto

import com.apx6.domain.constants.CmdNetworkKeyTags
import com.google.gson.annotations.SerializedName

data class CmdLocationMeta (

    @field:SerializedName(CmdNetworkKeyTags.PAGEABLE_COUNT)
    val pageableCount: Int = 0,

    @field:SerializedName(CmdNetworkKeyTags.TOTAL_COUNT)
    val totalCount: Int = 0

)
