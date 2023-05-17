package com.apx6.domain.repository

import com.apx6.domain.dto.CmdSetting
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    suspend fun post(setting: CmdSetting): Boolean

    suspend fun postNotification(setting: CmdSetting): Boolean

    suspend fun getNotification(uid: Int): Flow<CmdSetting?>
}