package com.apx6.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apx6.data.dao.AttachmentDao
import com.apx6.data.dao.CategoryDao
import com.apx6.data.dao.CheckListDao
import com.apx6.data.dao.HistoryDao
import com.apx6.data.dao.NotificationDao
import com.apx6.data.dao.SettingDao
import com.apx6.data.dao.SyncDao
import com.apx6.data.dao.UserDao
import com.apx6.data.di.ApplicationScope
import com.apx6.domain.entities.Attachment
import com.apx6.domain.entities.Category
import com.apx6.domain.entities.CheckList
import com.apx6.domain.entities.History
import com.apx6.domain.entities.Notification
import com.apx6.domain.entities.Setting
import com.apx6.domain.entities.Sync
import com.apx6.domain.entities.User
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
        Setting::class,
        History::class
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
    abstract fun historyDao(): HistoryDao

    class Callback @Inject constructor(
        private val database: Provider<CmdDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}