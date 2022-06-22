package com.apx6.chipmunk.app.di.network

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * TaskNetworkModule
 */

@Module
@InstallIn(SingletonComponent::class)
object TaskNetworkModule {
//
//    @Singleton
//    @Provides
//    @TaskBaseUrl
//    fun provideTaskBaseUrl() = MpConstants.Uri.TASK
//
//    /**
//     * @Desc Retrofit Clients
//     */
//    @Provides
//    @Singleton
//    @AccountService
//    fun provideAccountRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @GeneralHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
//    ): Retrofit {
//        return retrofitBuilder(baseUrl, converterFactory, okHttpClient)
//    }
//
//    @Provides
//    @Singleton
//    @SpaceService
//    fun provideSpaceRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @GeneralHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
//    ): Retrofit {
//        return retrofitBuilder(baseUrl, converterFactory, okHttpClient)
//    }
//
//    @Provides
//    @Singleton
//    @BoardService
//    fun provideBoardRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @GeneralHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
//    ): Retrofit {
//        return retrofitBuilder(baseUrl, converterFactory, okHttpClient)
//    }
//
//    @Provides
//    @Singleton
//    @SectionService
//    fun provideSectionRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @GeneralHttpClient okHttpClient:
//        OkHttpClient, converterFactory: Converter.Factory
//    ): Retrofit {
//        return retrofitBuilder(baseUrl, converterFactory, okHttpClient)
//    }
//
//    @Provides
//    @Singleton
//    @LabelService
//    fun provideLabelRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @GeneralHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
//    ): Retrofit {
//        return retrofitBuilder(baseUrl, converterFactory, okHttpClient)
//    }
//
//    @Provides
//    @Singleton
//    @CardService
//    fun provideCardRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @GeneralHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
//    ): Retrofit {
//        return retrofitBuilder(baseUrl, converterFactory, okHttpClient)
//    }
//
//    @Provides
//    @Singleton
//    @LogService
//    fun provideLogRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @GeneralHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
//    ): Retrofit {
//        return retrofitBuilder(baseUrl, converterFactory, okHttpClient)
//    }
//
//    @Provides
//    @Singleton
//    @AttachService
//    fun provideAttachRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @GeneralHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
//    ): Retrofit {
//        return retrofitBuilder(baseUrl, converterFactory, okHttpClient)
//    }
//
//    @Provides
//    @Singleton
//    @AttachUploadService
//    fun provideAttachUploadRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @ProgressHttpClient okHttpClient: OkHttpClient
//    ): Retrofit {
//        return retrofitAttachBuilder(baseUrl, okHttpClient)
//    }
//
//    @Provides
//    @Singleton
//    @AttachDownloadService
//    fun provideAttachDownloadRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @ProgressHttpClient okHttpClient: OkHttpClient
//    ): Retrofit {
//        return retrofitAttachBuilder(baseUrl, okHttpClient)
//    }
//
//    @Provides
//    @Singleton
//    @BatchService
//    fun provideBatchRetrofitClient(
//        @TaskBaseUrl baseUrl: String,
//        @GeneralHttpClient okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
//    ): Retrofit {
//        return retrofitBuilder(baseUrl, converterFactory, okHttpClient)
//    }
//
//    /**
//     * @Desc API Services
//     */
//    @Singleton
//    @Provides
//    fun provideAccountApiService(
//        @AccountService retrofit: Retrofit
//    ): AccountApiService = retrofit.create(AccountApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideSpaceApiService(
//        @SpaceService retrofit: Retrofit
//    ): SpaceApiService = retrofit.create(SpaceApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideBoardApiService(
//        @BoardService retrofit: Retrofit
//    ): BoardApiService = retrofit.create(BoardApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideSectionApiService(
//        @SectionService retrofit: Retrofit
//    ): SectionApiService = retrofit.create(SectionApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideLabelApiService(
//        @LabelService retrofit: Retrofit
//    ): LabelApiService = retrofit.create(LabelApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideCardApiService(
//        @CardService retrofit: Retrofit
//    ): CardApiService = retrofit.create(CardApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideAttachApiService(
//        @AttachService retrofit: Retrofit
//    ): AttachApiService = retrofit.create(AttachApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideAttachUploadApiService(
//        @AttachUploadService retrofit: Retrofit
//    ): AttachUploadApiService = retrofit.create(AttachUploadApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideAttachDownloadApiService(
//        @AttachDownloadService retrofit: Retrofit
//    ): AttachDownloadApiService = retrofit.create(AttachDownloadApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideBatchApiService(
//        @BatchService retrofit: Retrofit
//    ): BatchApiService = retrofit.create(BatchApiService::class.java)
//
//    /**
//     * @Desc 일반 Retrofit Builder
//     */
//    private fun retrofitBuilder(
//        baseUrl: String,
//        converterFactory: Converter.Factory,
//        okHttpClient: OkHttpClient
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(converterFactory)
//            .client(okHttpClient)
//            .build()
//    }
//
//    /**
//     * @Desc 첨부파일 전용 Retrofit Builder
//     */
//    private fun retrofitAttachBuilder(
//        baseUrl: String,
//        okHttpClient: OkHttpClient
//    ): Retrofit {
//        val gson = GsonBuilder().setLenient().create()
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(okHttpClient)
//            .build()
//    }
}