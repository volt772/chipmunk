package com.apx6.chipmunk.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ChipmunkApp : Application() {

//    @Inject lateinit var crashlytics: MpCrashlytics

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

//        crashlytics.collectionEnabled(!BuildConfig.DEBUG)
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}