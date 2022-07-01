package com.apx6.chipmunk.app

import android.app.Application
import com.apx6.chipmunk.R
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ChipmunkApp : Application() {

//    @Inject lateinit var crashlytics: MpCrashlytics

    override fun onCreate() {
        super.onCreate()

//        appContext = applicationContext

        KakaoSdk.init(this, getString(R.string.kakao_app_key))

//        crashlytics.collectionEnabled(!BuildConfig.DEBUG)
    }
//
//    companion object {
//        lateinit var appContext: Context
//            private set
//    }
}