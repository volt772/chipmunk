package com.apx6.data.di

import com.apx6.data.data_source.CheckListDataSource
import com.apx6.data.data_source.CheckListDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class PagingModule {

    @Binds
    @Singleton
    abstract fun bindCheckListDataSource(impl: CheckListDataSourceImpl): CheckListDataSource
}