package com.apx6.domain.dto

import com.apx6.domain.constants.CmdSettingType


data class CmdSetting (

    val id: Int = 0,

    val uid: Int = 0,

    val key: CmdSettingType,

    val value: String= "",

    val ext1: String= "",

    val ext2: String= "",

    val ext3: String= "",

    val setDate: Long = 0L

) {
    companion object {
        fun default(): CmdSetting {
            return CmdSetting(
                id = 0,
                uid = 0,
                key = CmdSettingType.NONE,
                value = "",
                ext1 = "",
                ext2 = "",
                ext3 = "",
                setDate = 0L
            )
        }
    }
}
