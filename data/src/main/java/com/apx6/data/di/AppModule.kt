package com.apx6.data.di

import com.apx6.domain.crashlytics.CmdCrashlytics
import com.apx6.data.crashlytics.CmdCrashlyticsImpl
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

//    @Binds
//    @Singleton
//    abstract fun bindMpPreferences(impl: MpPreferenceImpl): MpPreference
//
//    @Binds
//    @Singleton
//    abstract fun bindMpUiUtils(impl: MpUiUtilsImpl): MpUiUtils
//
//    @Binds
//    @Singleton
//    abstract fun bindMpAttachUtils(impl: MpAttachUtilsImpl): MpAttachUtils
//
//    @Binds
//    @Singleton
//    abstract fun bindMpdStorageUtils(impl: MpdStorageUtilsImpl): MpdStorageUtils
//
//    @Binds
//    @Singleton
//    abstract fun bindMpdDataUtils(impl: MpdUtilsImpl): MpdUtils
//
//    @Binds
//    @Singleton
//    abstract fun bindMpPermissionUtils(impl: MpPermissionUtilsImpl): MpPermissionUtils
//
//    @Binds
//    @Singleton
//    abstract fun bindMpdPurgeRows(impl: MpdPurgeRowsImpl): MpdPurgeRows
//
//    @Binds
//    @Singleton
//    abstract fun bindMpdError(impl: MpdErrorImpl): MpdError
//
//    @Binds
//    @Singleton
//    abstract fun bindMpdResponseRefinery(impl: MpdResponseRefineryImpl): MpdResponseRefinery
//
//    @Binds
//    @Singleton
//    abstract fun bindAttachmentUploader(impl: AttachmentUploaderImpl): AttachmentUploader
//
//    @Binds
//    @Singleton
//    abstract fun bindAttachmentDownloader(impl: AttachmentDownloaderImpl): AttachmentDownloader
//
//    @Binds
//    @Singleton
//    abstract fun bindMpdThreadExecutors(impl: MpdThreadExecutorsImpl): MpdThreadExecutors
//
//    @Binds
//    @Singleton
//    abstract fun bindProgressListener(impl: ProgressListenerImpl): ProgressListener
//
//    @Binds
//    @Singleton
//    abstract fun bindWebSocketListener(impl: WebSocketManagerImpl): WebSocketManager
//
//    @Binds
//    @Singleton
//    abstract fun bindRemoteConfigManager(impl: MpdRemoteConfigManagerImpl): MpdRemoteConfigManager
//
//    @Binds
//    @Singleton
//    abstract fun bindApplicationInfo(impl: MpdApplicationInfoImpl): MpdApplicationInfo
//
//    @Binds
//    @Singleton
//    abstract fun bindParseUtils(impl: MpdParseUtilsImpl): MpdParseUtils
//
//    @Binds
//    @Singleton
//    abstract fun bindNetworkHelper(impl: MpdNetworkHelperImpl): MpdNetworkHelper
//
//    @Binds
//    @Singleton
//    abstract fun bindSpannableStringUtils(impl: MpSpannableStringUtilsImpl): MpSpannableStringUtils
//
//    @Binds
//    @Singleton
//    abstract fun bindLogUtils(impl: MpLogUtilsImpl): MpLogUtils
//
//    @Binds
//    @Singleton
//    abstract fun bindSupportRequestUtils(impl: MpdSupportRequestUtilsImpl): MpdSupportRequestUtils
//
//
//    @Binds
//    @Singleton
//    abstract fun bindMpdCheckExistence(impl: MpdCheckExistenceImpl): MpdCheckExistence
//
//    @Binds
//    @Singleton
//    abstract fun bindMpDateUtils(impl: MpDateUtilsImpl): MpDateUtils
//
//    @Binds
//    @Singleton
//    abstract fun bindMpKoreanCharUtils(impl: MpKoreanCharUtilsImpl): MpKoreanCharUtils
//
//    @Binds
//    @Singleton
//    abstract fun bindMpdCountingChanger(impl: MpdCountingChangerImpl): MpdCountingChanger
}