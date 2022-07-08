package com.apx6.data.di

import com.apx6.data.mapper.*
import com.apx6.domain.mapper.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * MapperModule
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindUserMapper(impl: UserMapperImpl): UserMapper

    @Binds
    @Singleton
    abstract fun bindCategoryMapper(impl: CategoryMapperImpl): CategoryMapper

    @Binds
    @Singleton
    abstract fun bindTaskMapper(impl: TaskMapperImpl): TaskMapper

    @Binds
    @Singleton
    abstract fun bindNotificationMapper(impl: NotificationMapperImpl): NotificationMapper

    @Binds
    @Singleton
    abstract fun bindSyncMapper(impl: SyncMapperImpl): SyncMapper

//
//    @Binds
//    @Singleton
//    abstract fun bindMpAccountMapper(impl: MpdAccountMapperImpl): MpdAccountMapper
//
//    @Binds
//    @Singleton
//    abstract fun bindMpSpaceMapper(impl: MpdSpaceMapperImpl): MpdSpaceMapper
//
//    @Binds
//    @Singleton
//    abstract fun bindMpBoardMapper(impl: MpdBoardMapperImpl): MpdBoardMapper
//
//    @Binds
//    @Singleton
//    abstract fun bindMpSectionMapper(impl: MpdSectionMapperImpl): MpdSectionMapper
//
//    @Binds
//    @Singleton
//    abstract fun bindMpLabelMapper(impl: MpdLabelMapperImpl): MpdLabelMapper
//
//    @Binds
//    @Singleton
//    abstract fun bindMpCardMapper(impl: MpdCardMapperImpl): MpdCardMapper
//
//    @Binds
//    @Singleton
//    abstract fun bindMpAssigneeMapper(impl: MpdAssigneeMapperImpl): MpdAssigneeMapper
//
//    @Binds
//    @Singleton
//    abstract fun bindMpCardLabelMapper(impl: MpdCardLabelMapperImpl): MpdCardLabelMapper
//
//    @Binds
//    @Singleton
//    abstract fun bindMpCheckListMapper(impl: MpdCheckListMapperImpl): MpdCheckListMapper
//
//    @Binds
//    @Singleton
//    abstract fun bindMpAttachmentListMapper(impl: MpdAttachmentMapperImpl): MpdAttachmentMapper
//
//    @Binds
//    @Singleton
//    abstract fun bindMpLogMapper(impl: MpdLogMapperImpl): MpdLogMapper

}