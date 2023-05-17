package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.constants.CmdSettingType
import com.apx6.domain.dto.CmdNotification
import com.apx6.domain.dto.CmdSetting
import com.apx6.domain.entities.Setting
import kotlinx.coroutines.flow.Flow


@Dao
abstract class SettingDao : BaseDao<Setting>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
    @Query(value =
    """
    SELECT * 
    FROM ${Setting.TABLE_NAME} 
    WHERE uid = :uid 
    AND `key` = :key
    """
    )
    abstract fun getSetting(uid: Int, key: CmdSettingType): CmdSetting?

    @Query(value =
    """
    SELECT * 
    FROM ${Setting.TABLE_NAME} 
    WHERE uid = :uid 
    AND `key` = :key
    """
    )
    abstract fun fetchSetting(uid: Int, key: CmdSettingType): Flow<CmdSetting?>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/

    /* ▼ TEST ONLY =======================================================================================================================*/
}