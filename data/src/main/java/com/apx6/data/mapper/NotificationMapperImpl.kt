package com.apx6.data.mapper

import com.apx6.domain.dto.CmdNotification
import com.apx6.domain.entities.Notification
import com.apx6.domain.mapper.NotificationMapper
import javax.inject.Inject

class NotificationMapperImpl @Inject constructor(

): NotificationMapper {
    override suspend fun notificationToEntity(
        notification: CmdNotification
    ): Notification {
        return Notification(
            id = notification.id,
            clId = notification.clId,
            period = notification.period
        )
    }
}