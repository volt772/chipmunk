package com.apx6.domain.repository

import com.apx6.domain.dto.CmdNotification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {

    /* Combined*/
//    suspend fun notification(notification: CmdNotification): Flow<Resource<CmdNotification>>

    suspend fun postNotification(notification: CmdNotification)

    suspend fun getNotification(tid: Int): Flow<CmdNotification?>

    suspend fun patchNotification(notification: CmdNotification): Boolean

    suspend fun delNotification(notification: CmdNotification): Boolean

}