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
    abstract fun bindCheckListMapper(impl: CheckListMapperImpl): CheckListMapper

    @Binds
    @Singleton
    abstract fun bindSyncMapper(impl: SyncMapperImpl): SyncMapper

    @Binds
    @Singleton
    abstract fun bindAttachMapper(impl: AttachMapperImpl): AttachMapper

    @Binds
    @Singleton
    abstract fun bindSettingMapper(impl: SettingMapperImpl): SettingMapper

}