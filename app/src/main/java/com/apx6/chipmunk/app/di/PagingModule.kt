package com.apx6.chipmunk.app.di

import com.apx6.chipmunk.app.data.category.CategoryRemoteDataSource
import com.apx6.chipmunk.app.data.category.CategoryRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * PagingModule
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class PagingModule {

    @Binds
    @Singleton
    abstract fun bindCategoryRemoteDataSource(impl: CategoryRemoteDataSourceImpl): CategoryRemoteDataSource

}
