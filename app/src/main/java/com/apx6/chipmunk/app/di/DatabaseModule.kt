package com.apx6.chipmunk.app.di

import android.content.Context
import androidx.room.Room
import com.apx6.chipmunk.app.db.CmDatabase
import com.apx6.chipmunk.domain.constants.CmdConstants.DB.DB_NAME
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
        callback: CmDatabase.Callback
    ): CmDatabase {

        return Room.databaseBuilder( appContext, CmDatabase::class.java, DB_NAME)
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
    fun provideAttachmentDao(database: CmDatabase) = database.attachmentDao()

    @Provides
    @Singleton
    fun provideCategoryDao(database: CmDatabase) = database.categoryDao()

    @Provides
    @Singleton
    fun provideNotificationDao(database: CmDatabase) = database.notificationDao()

    @Provides
    @Singleton
    fun provideSyncDao(database: CmDatabase) = database.syncDao()

    @Provides
    @Singleton
    fun provideTaskDao(database: CmDatabase) = database.taskDao()

    @Provides
    @Singleton
    fun provideUserDao(database: CmDatabase) = database.userDao()

}