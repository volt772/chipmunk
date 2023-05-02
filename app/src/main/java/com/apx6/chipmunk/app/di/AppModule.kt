package com.apx6.chipmunk.app.di

import android.app.Application
import android.content.Context
import com.apx6.chipmunk.app.utils.CmKoreanCharUtils
import com.apx6.chipmunk.app.utils.CmKoreanCharUtilsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindContext(application: Application): Context

    @Binds
    @Singleton
    abstract fun bindCmKoreanCharUtils(impl: CmKoreanCharUtilsImpl): CmKoreanCharUtils

}