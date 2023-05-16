package com.apx6.domain.entities.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.apx6.domain.constants.CmdSettingType

@ProvidedTypeConverter
object CmdSettingTypeConverter {

    @JvmStatic
    @TypeConverter
    fun toCmdSettingType(type: String) = when (type) {
        CmdSettingType.USER.type -> CmdSettingType.USER
        CmdSettingType.NOTIFICATION.type -> CmdSettingType.NOTIFICATION
        else -> CmdSettingType.NONE
    }

    @JvmStatic
    @TypeConverter
    fun fromCmdSettingType(type: CmdSettingType) = type.type
}
