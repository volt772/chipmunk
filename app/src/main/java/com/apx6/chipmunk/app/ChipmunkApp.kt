package com.apx6.chipmunk.app

import android.app.Application
import android.content.Context
import com.apx6.chipmunk.R
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ChipmunkApp : Application() {

//    @Inject lateinit var crashlytics: MpCrashlytics

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        KakaoSdk.init(this, getString(R.string.kakao_app_key))

        /* 플랫폼 등록용*/
        val keyHash = Utility.getKeyHash(this)

//        crashlytics.collectionEnabled(!BuildConfig.DEBUG)
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}