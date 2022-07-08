package com.apx6.data.repository

import com.apx6.data.dao.NotificationDao
import com.apx6.domain.dto.CmdNotification
import com.apx6.domain.entities.Notification
import com.apx6.domain.mapper.NotificationMapper
import com.apx6.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDao: NotificationDao,
    private val notificationMapper: NotificationMapper
): NotificationRepository {

//    override suspend fun notification(notification: CmdNotification): Flow<Resource<CmdNotification>> {
//        return object: BoundaryRepository<CmdNotification, CmdNotification>() {
//            override suspend fun saveRemoteData(response: CmdNotification) {
////                val entity = convertToEntity(task)
////                taskDao.insertOrUpdate(entity)
//            }
//
//            override fun fetchFromLocal(): Flow<CmdNotification> {
//                return notificationDao.getNotification()
//            }
//
//        }.asFlow()
//    }

    override suspend fun postNotification(notification: CmdNotification) {
        val entity = convertToEntity(notification)
        notificationDao.insertOrUpdate(entity)
    }

    override suspend fun getNotification(tid: Int): Flow<CmdNotification?> {
        return notificationDao.getNotification(tid)
    }

    override suspend fun patchNotification(notification: CmdNotification): Boolean {
        val entity = convertToEntity(notification)
        return notificationDao.update(entity) > 0
    }

    override suspend fun delNotification(notification: CmdNotification): Boolean {
        val entity = convertToEntity(notification)
        return notificationDao.delete(entity) > 0
    }

    private suspend fun convertToEntity(notification: CmdNotification): Notification {
        return notificationMapper.notificationToEntity(notification)
    }

}