package com.apx6.data.repository

import com.apx6.data.dao.NotificationDao
import com.apx6.domain.mapper.NotificationMapper
import com.apx6.domain.repository.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDao: NotificationDao,
    private val notificationMapper: NotificationMapper
): NotificationRepository {

}