package com.apx6.data.di

import com.apx6.data.repository.UserRepositoryImpl
import com.apx6.domain.repository.UserRepository
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
//
//    @Binds
//    @Singleton
//    abstract fun bindWebSyncRepository(impl: WebSyncRepositoryImpl): WebSyncRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindCrmRepository(impl: CrmRepositoryImpl): CrmRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindLabelRepository(impl: LabelRepositoryImpl): LabelRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindLabelActionRepository(impl: LabelActionRepositoryImpl): LabelActionRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindBoardRepository(impl: BoardRepositoryImpl): BoardRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindSectionRepository(impl: SectionRepositoryImpl): SectionRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindSectionActionRepository(impl: SectionActionRepositoryImpl): SectionActionRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindSpaceRepository(impl: SpaceRepositoryImpl): SpaceRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindCardRepository(impl: CardRepositoryImpl): CardRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindCardActionRepository(impl: CardActionRepositoryImpl): CardActionRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindAssigneeRepository(impl: AssigneeRepositoryImpl): AssigneeRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindCheckListRepository(impl: CheckListRepositoryImpl): CheckListRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindLogRepository(impl: LogRepositoryImpl): LogRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindLogActionRepository(impl: LogActionRepositoryImpl): LogActionRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindCardLabelRepository(impl: CardLabelRepositoryImpl): CardLabelRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindAttachRepository(impl: AttachRepositoryImpl): AttachRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindAttachUploadRepository(impl: AttachUploadRepositoryImpl): AttachUploadRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindAttachDownloadRepository(impl: AttachDownloadRepositoryImpl): AttachDownloadRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindPushConnectionRepository(impl: PushConnectionRepositoryImpl): PushConnectionRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindBatchRepository(impl: BatchRepositoryImpl): BatchRepository
//
}