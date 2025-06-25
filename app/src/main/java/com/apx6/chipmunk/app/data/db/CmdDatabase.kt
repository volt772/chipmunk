package com.apx6.chipmunk.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apx6.chipmunk.app.data.dao.AttachmentDao
import com.apx6.chipmunk.app.data.dao.CategoryDao
import com.apx6.chipmunk.app.data.dao.CheckListDao
import com.apx6.chipmunk.app.data.dao.HistoryDao
import com.apx6.chipmunk.app.data.dao.NotificationDao
import com.apx6.chipmunk.app.data.dao.SettingDao
import com.apx6.chipmunk.app.data.dao.SyncDao
import com.apx6.chipmunk.app.data.dao.UserDao
import com.apx6.chipmunk.app.domain.entities.Attachment
import com.apx6.chipmunk.app.domain.entities.Category
import com.apx6.chipmunk.app.domain.entities.CheckList
import com.apx6.chipmunk.app.domain.entities.History
import com.apx6.chipmunk.app.domain.entities.Notification
import com.apx6.chipmunk.app.domain.entities.Setting
import com.apx6.chipmunk.app.domain.entities.Sync
import com.apx6.chipmunk.app.domain.entities.User


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
}