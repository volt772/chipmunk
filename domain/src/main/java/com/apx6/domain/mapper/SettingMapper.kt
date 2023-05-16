package com.apx6.domain.mapper

import com.apx6.domain.dto.CmdSetting
import com.apx6.domain.entities.Setting

interface SettingMapper {

    suspend fun settingToEntity(setting: CmdSetting): Setting

}