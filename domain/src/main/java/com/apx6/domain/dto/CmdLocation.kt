package com.apx6.domain.dto

import com.apx6.domain.constants.CmdNetworkKeyTags
import com.google.gson.annotations.SerializedName

data class CmdLocation (

    @field:SerializedName(CmdNetworkKeyTags.DOCUMENTS)
    val documents: List<CmdLocationDoc>?= emptyList(),

    @field:SerializedName(CmdNetworkKeyTags.META)
    val meta: CmdLocationMeta

)
