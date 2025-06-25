package com.apx6.chipmunk.app.di

import com.apx6.chipmunk.app.data.mapper.AttachMapperImpl
import com.apx6.chipmunk.app.data.mapper.CategoryMapperImpl
import com.apx6.chipmunk.app.data.mapper.CheckListMapperImpl
import com.apx6.chipmunk.app.data.mapper.SettingMapperImpl
import com.apx6.chipmunk.app.data.mapper.SyncMapperImpl
import com.apx6.chipmunk.app.data.mapper.UserMapperImpl
import com.apx6.chipmunk.app.domain.mapper.AttachMapper
import com.apx6.chipmunk.app.domain.mapper.CategoryMapper
import com.apx6.chipmunk.app.domain.mapper.CheckListMapper
import com.apx6.chipmunk.app.domain.mapper.SettingMapper
import com.apx6.chipmunk.app.domain.mapper.SyncMapper
import com.apx6.chipmunk.app.domain.mapper.UserMapper
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