package com.apx6.data.di

import com.apx6.domain.crashlytics.CmdCrashlytics
import com.apx6.data.crashlytics.CmdCrashlyticsImpl
import com.apx6.data.response.CmdResponseRefineryImpl
import com.apx6.domain.response.CmdResponseRefinery
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
    abstract fun bindCrashlytics(impl: CmdCrashlyticsImpl): CmdCrashlytics

    @Binds
    @Singleton
    abstract fun bindCmdResponseRefinery(impl: CmdResponseRefineryImpl): CmdResponseRefinery
}