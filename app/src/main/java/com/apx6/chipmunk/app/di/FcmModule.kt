package com.apx6.chipmunk.app.di

import com.apx6.chipmunk.app.fcm.FcmHelper
import com.apx6.chipmunk.app.fcm.FcmHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * FCM Module
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class FcmModule {

    @Binds
    @Singleton
    abstract fun bindFcmHelper(impl: FcmHelperImpl): FcmHelper

}