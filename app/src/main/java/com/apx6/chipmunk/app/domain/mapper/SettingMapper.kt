package com.apx6.chipmunk.app.domain.mapper

import com.apx6.chipmunk.app.domain.dto.CmdSetting
import com.apx6.chipmunk.app.domain.entities.Setting

interface SettingMapper {

    suspend fun settingToEntity(setting: CmdSetting, sid: Int): Setting

}