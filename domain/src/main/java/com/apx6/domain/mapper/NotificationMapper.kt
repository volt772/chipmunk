package com.apx6.domain.mapper

import com.apx6.domain.dto.CmdNotification
import com.apx6.domain.entities.Notification

interface NotificationMapper {

    suspend fun notificationToEntity(notification: CmdNotification): Notification

}