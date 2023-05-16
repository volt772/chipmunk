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
        Attachment::class,
        Category::class,
        Notification::class,
        Sync::class,
        CheckList::class,
        User::class,
        Setting::class
    ],
    version = 1,
    exportSchema = false
)

abstract class CmdDatabase : RoomDatabase() {

    abstract fun attachmentDao(): AttachmentDao
    abstract fun categoryDao(): CategoryDao
    abstract fun notificationDao(): NotificationDao
    abstract fun syncDao(): SyncDao
    abstract fun checkListDao(): CheckListDao
    abstract fun userDao(): UserDao
    abstract fun settingDao(): SettingDao

    class Callback @Inject constructor(
        private val database: Provider<CmdDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}