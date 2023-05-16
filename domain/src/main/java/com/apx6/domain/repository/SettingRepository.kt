package com.apx6.domain.repository

import com.apx6.domain.dto.CmdSetting

interface SettingRepository {

    suspend fun post(setting: CmdSetting): Boolean

}