package com.apx6.data.repository

import com.apx6.data.dao.SettingDao
import com.apx6.domain.dto.CmdSetting
import com.apx6.domain.entities.Setting
import com.apx6.domain.mapper.SettingMapper
import com.apx6.domain.repository.SettingRepository
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val settingDao: SettingDao,
    private val settingMapper: SettingMapper
): SettingRepository {

    override suspend fun post(setting: CmdSetting): Boolean {
        val entity = convertToEntity(setting)
        return settingDao.insertOrUpdate(entity) > 0
    }

    private suspend fun convertToEntity(setting: CmdSetting): Setting {
        return settingMapper.settingToEntity(setting)
    }
}