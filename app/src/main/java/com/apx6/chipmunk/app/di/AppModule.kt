package com.apx6.chipmunk.app.di

import android.app.Application
import android.content.Context
import com.apx6.chipmunk.app.data.crashlytics.CmdCrashlyticsImpl
import com.apx6.chipmunk.app.data.response.CmdResponseRefineryImpl
import com.apx6.chipmunk.app.domain.crashlytics.CmdCrashlytics
import com.apx6.chipmunk.app.domain.response.CmdResponseRefinery
import com.apx6.chipmunk.app.domain.utils.CmdApplicationInfo
import com.apx6.chipmunk.app.domain.utils.CmdApplicationInfoImpl
import com.apx6.chipmunk.app.domain.utils.CmdParseUtils
import com.apx6.chipmunk.app.domain.utils.CmdParseUtilsImpl
import com.apx6.chipmunk.app.domain.utils.CmdRemoteConfigMgr
import com.apx6.chipmunk.app.domain.utils.CmdRemoteConfigMgrImpl
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
    abstract fun bindCmdRemoteConfigMgr(impl: CmdRemoteConfigMgrImpl): CmdRemoteConfigMgr

    @Binds
    @Singleton
    abstract fun bindCmdApplicationInfo(impl: CmdApplicationInfoImpl): CmdApplicationInfo

    @Binds
    @Singleton
    abstract fun bindParseUtils(impl: CmdParseUtilsImpl): CmdParseUtils

    @Binds
    @Singleton
    abstract fun bindCrashlytics(impl: CmdCrashlyticsImpl): CmdCrashlytics

    @Binds
    @Singleton
    abstract fun bindCmdResponseRefinery(impl: CmdResponseRefineryImpl): CmdResponseRefinery

}