package com.apx6.data.repository

import com.apx6.data.dao.SettingDao
import com.apx6.domain.constants.CmdSettingType
import com.apx6.domain.dto.CmdSetting
import com.apx6.domain.entities.Setting
import com.apx6.domain.mapper.SettingMapper
import com.apx6.domain.repository.MoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoreRepositoryImpl @Inject constructor(
    private val settingDao: SettingDao,
    private val settingMapper: SettingMapper
): MoreRepository {

    override suspend fun postSetting(setting: CmdSetting): Boolean {
        val sd = settingDao.getSetting(setting.uid, setting.key)
        val sid = sd?.id ?: 0

        val entity = convertToEntity(setting, sid)
        return settingDao.insertOrUpdate(entity) > 0
    }

    override suspend fun fetchSetting(uid: Int, key: CmdSettingType): Flow<CmdSetting?> {
        return settingDao.fetchSetting(uid, key)
    }

    private suspend fun convertToEntity(setting: CmdSetting, sid: Int): Setting {
        return settingMapper.settingToEntity(setting, sid)
    }
}