package com.apx6.chipmunk.app.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * DatabaseModule
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(
//        @ApplicationContext appContext: Context,
//        callback: MpDatabase.Callback
//    ): MpDatabase {
//
//        return Room.databaseBuilder( appContext, MpDatabase::class.java, DB_NAME)
////            .addMigrations(MIGRATION_1_TO_2)
//            .fallbackToDestructiveMigration()
//            .addCallback(callback)
//            .build()
//    }
//
//    @ApplicationScope
//    @Provides
//    @Singleton
//    fun provideApplicationScope(): CoroutineScope {
//        return CoroutineScope(SupervisorJob())
//    }
//
//    /**
//     * @Dao
//     */
//    @Provides
//    @Singleton
//    fun provideAccountDao(database: MpDatabase) = database.accountDao()
//
//    @Provides
//    @Singleton
//    fun provideSpacesDao(database: MpDatabase) = database.spacesDao()
//
//    @Provides
//    @Singleton
//    fun provideSpaceMemberDao(database: MpDatabase) = database.spaceMembersDao()
//
//    @Provides
//    @Singleton
//    fun provideBoardsDao(database: MpDatabase) = database.boardsDao()
//
//    @Provides
//    @Singleton
//    fun provideSectionsDao(database: MpDatabase) = database.sectionsDao()
//
//    @Provides
//    @Singleton
//    fun provideLabelsDao(database: MpDatabase) = database.labelsDao()
//
//    @Provides
//    @Singleton
//    fun provideCardsDao(database: MpDatabase) = database.cardsDao()
//
//    @Provides
//    @Singleton
//    fun provideCheckListsDao(database: MpDatabase) = database.checkListsDao()
//
//    @Provides
//    @Singleton
//    fun provideAssigneeMembersDao(database: MpDatabase) = database.assigneeMembersDao()
//
//    @Provides
//    @Singleton
//    fun provideAssigneesDao(database: MpDatabase) = database.assigneesDao()
//
//    @Provides
//    @Singleton
//    fun provideAssigneesUnAuthorizedDao(database: MpDatabase) = database.assigneesUnAuthorizedDao()
//
//    @Provides
//    @Singleton
//    fun provideCardLabelsDao(database: MpDatabase) = database.cardLabelsDao()
//
//    @Provides
//    @Singleton
//    fun provideAttachmentsDao(database: MpDatabase) = database.attachmentsDao()
//
//    @Provides
//    @Singleton
//    fun provideAttachmentMetasDao(database: MpDatabase) = database.attachmentMetasDao()
//
//    @Provides
//    @Singleton
//    fun provideLogsDao(database: MpDatabase) = database.logsDao()
//
//    @Provides
//    @Singleton
//    fun provideRemoteKeyDao(database: MpDatabase) = database.remoteKeyDao()
//
//    @Provides
//    @Singleton
//    fun provideSyncDao(database: MpDatabase) = database.syncDao()
//
//    @Provides
//    @Singleton
//    fun provideSectionFiltersDao(database: MpDatabase) = database.sectionFiltersDao()
}