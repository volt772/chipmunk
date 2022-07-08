package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.dto.CmdNotification
import com.apx6.domain.entities.Notification
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.TestOnly


@Dao
abstract class NotificationDao : BaseDao<Notification>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
    @Query("SELECT * FROM ${Notification.TABLE_NAME} WHERE tid = :tid")
    abstract fun getNotification(tid: Int): Flow<CmdNotification?>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/

    /* ▼ TEST ONLY =======================================================================================================================*/
    @TestOnly
    @Query("SELECT * FROM ${Notification.TABLE_NAME} LIMIT 1")
    abstract fun testGetNotification(): CmdNotification?

}