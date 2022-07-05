package com.apx6.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apx6.data.dao.*
import com.apx6.data.di.ApplicationScope
import com.apx6.domain.entities.*
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider


@Database(
    entities = [
        AttachmentEntity::class,
        CategoryEntity::class,
        NotificationEntity::class,
        SyncEntity::class,
        TaskEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class CmdDatabase : RoomDatabase() {

    abstract fun attachmentDao(): AttachmentDao
    abstract fun categoryDao(): CategoryDao
    abstract fun notificationDao(): NotificationDao
    abstract fun syncDao(): SyncDao
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao

    class Callback @Inject constructor(
        private val database: Provider<CmdDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}