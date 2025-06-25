package com.apx6.chipmunk.app.domain.repository

import com.apx6.chipmunk.app.domain.constants.CmdSettingType
import com.apx6.chipmunk.app.domain.dto.CmdSetting
import kotlinx.coroutines.flow.Flow

interface MoreRepository {

    suspend fun postSetting(setting: CmdSetting): Boolean

    suspend fun fetchSetting(uid: Int, key: CmdSettingType): Flow<CmdSetting?>

}