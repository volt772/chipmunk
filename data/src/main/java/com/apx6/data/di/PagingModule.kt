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

//    @Binds
//    @Singleton
//    abstract fun bindRemoteKeyOperator(impl: RemoteKeyOperatorImpl): RemoteKeyOperator
//
//    @Binds
//    @Singleton
//    abstract fun bindSpaceMemberRemoteDataSource(impl: SpaceMemberRemoteDataSourceImpl): SpaceMemberRemoteDataSource
//
//    @Binds
//    @Singleton
//    abstract fun bindAssigneeMemberRemoteDataSource(impl: AssigneeMemberRemoteDataSourceImpl): AssigneeMemberRemoteDataSource
//
//    @Binds
//    @Singleton
//    abstract fun bindCardRemoteDataSource(impl: CardRemoteDataSourceImpl): CardRemoteDataSource
//
//    @Binds
//    @Singleton
//    abstract fun bindLogRemoteDataSource(impl: LogRemoteDataSourceImpl): LogRemoteDataSource
}