package com.apx6.data.repository

import com.apx6.data.dao.SettingDao
import com.apx6.domain.repository.SettingRepository
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val settingDao: SettingDao
): SettingRepository {
}