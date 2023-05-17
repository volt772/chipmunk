package com.apx6.domain.repository

import com.apx6.domain.constants.CmdSettingType
import com.apx6.domain.dto.CmdSetting
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    suspend fun postSetting(setting: CmdSetting): Boolean

    suspend fun fetchSetting(uid: Int, key: CmdSettingType): Flow<CmdSetting?>

}