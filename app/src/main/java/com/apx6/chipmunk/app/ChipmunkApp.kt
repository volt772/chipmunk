package com.apx6.chipmunk.app

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.getTimeUsingInWorkRequest
import com.apx6.chipmunk.app.worker.ReminderWorker
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltAndroidApp
class ChipmunkApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        KakaoSdk.init(this, getString(R.string.kakao_app_key))

        initWorkManager()
    }

    private fun initWorkManager() {
        val rw = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(getTimeUsingInWorkRequest(), TimeUnit.MILLISECONDS)
            .addTag(WORK_TAG)
            .build()

        WorkManager.getInstance(this).enqueue(rw)
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()

    companion object {
        lateinit var appContext: Context
            private set

        const val WORK_TAG = "chipmunk_reminder"
    }
}