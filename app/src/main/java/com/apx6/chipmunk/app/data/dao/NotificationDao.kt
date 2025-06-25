package com.apx6.chipmunk.app.data.dao

import androidx.room.Dao
import com.apx6.chipmunk.app.domain.entities.Notification


@Dao
abstract class NotificationDao : BaseDao<Notification>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
//    @Query("SELECT * FROM ${Notification.TABLE_NAME} WHERE clId = :clId")
//    abstract fun getNotification(clId: Int): Flow<CmdNotification?>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/

    /* ▼ TEST ONLY =======================================================================================================================*/
//    @TestOnly
//    @Query("SELECT * FROM ${Notification.TABLE_NAME} LIMIT 1")
//    abstract fun testGetNotification(): CmdNotification?

}