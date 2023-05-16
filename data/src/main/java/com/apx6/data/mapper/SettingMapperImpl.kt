package com.apx6.data.mapper

import com.apx6.domain.dto.CmdSetting
import com.apx6.domain.entities.Setting
import com.apx6.domain.mapper.SettingMapper
import javax.inject.Inject

class SettingMapperImpl @Inject constructor(

): SettingMapper {

    override suspend fun settingToEntity(setting: CmdSetting): Setting {
        return Setting(
            id = setting.id,
            uid = setting.uid,
            key = setting.key,
            value = setting.value,
            ext1 = setting.ext1,
            ext2 = setting.ext2,
            ext3 = setting.ext3,
            setDate = setting.setDate,
        )
    }
}