package com.apx6.data.di

import android.content.Context
import androidx.room.Room
import com.apx6.data.db.CmdDatabase
import com.apx6.domain.constants.CmdConstants.DB.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context,
        callback: CmdDatabase.Callback
    ): CmdDatabase {

        return Room.databaseBuilder( appContext, CmdDatabase::class.java, DB_NAME)
//            .addMigrations(MIGRATION_1_TO_2)
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

    /**
     * @Dao
     */
    @Provides
    @Singleton
    fun provideAttachmentDao(database: CmdDatabase) = database.attachmentDao()

    @Provides
    @Singleton
    fun provideCategoryDao(database: CmdDatabase) = database.categoryDao()

    @Provides
    @Singleton
    fun provideNotificationDao(database: CmdDatabase) = database.notificationDao()

    @Provides
    @Singleton
    fun provideSyncDao(database: CmdDatabase) = database.syncDao()

    @Provides
    @Singleton
    fun provideTaskDao(database: CmdDatabase) = database.taskDao()

    @Provides
    @Singleton
    fun provideUserDao(database: CmdDatabase) = database.userDao()

}