package com.apx6.chipmunk.app.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.apx6.chipmunk.app.fcm.FcmHelper
import com.apx6.domain.dto.CmdNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ReminderWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    val fcmHelper: FcmHelper
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        fcmHelper.sendNotification(CmdNotification.default())
        return Result.success()
    }

}