package com.apx6.chipmunk.app.di

import com.apx6.chipmunk.app.data.repository.AttachRepositoryImpl
import com.apx6.chipmunk.app.data.repository.CategoryRepositoryImpl
import com.apx6.chipmunk.app.data.repository.CheckListRepositoryImpl
import com.apx6.chipmunk.app.data.repository.HistoryRepositoryImpl
import com.apx6.chipmunk.app.data.repository.MoreRepositoryImpl
import com.apx6.chipmunk.app.data.repository.SyncRepositoryImpl
import com.apx6.chipmunk.app.data.repository.UserRepositoryImpl
import com.apx6.chipmunk.app.domain.repository.AttachRepository
import com.apx6.chipmunk.app.domain.repository.CategoryRepository
import com.apx6.chipmunk.app.domain.repository.CheckListRepository
import com.apx6.chipmunk.app.domain.repository.HistoryRepository
import com.apx6.chipmunk.app.domain.repository.MoreRepository
import com.apx6.chipmunk.app.domain.repository.SyncRepository
import com.apx6.chipmunk.app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * RepositoryModule
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindCheckListRepository(impl: CheckListRepositoryImpl): CheckListRepository

    @Binds
    @Singleton
    abstract fun bindSyncRepository(impl: SyncRepositoryImpl): SyncRepository

    @Binds
    @Singleton
    abstract fun bindAttachmentRepository(impl: AttachRepositoryImpl): AttachRepository

    @Binds
    @Singleton
    abstract fun bindSettingRepository(impl: MoreRepositoryImpl): MoreRepository

    @Binds
    @Singleton
    abstract fun bindHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository
}