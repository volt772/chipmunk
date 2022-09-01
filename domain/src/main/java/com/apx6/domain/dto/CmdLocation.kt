package com.apx6.domain.dto

import com.apx6.domain.constants.CmdNetworkKey
import com.google.gson.annotations.SerializedName

data class CmdLocation (

    @field:SerializedName(CmdNetworkKey.DOCUMENTS)
    val documents: List<CmdLocationDoc>?= emptyList()

)
