package com.apx6.chipmunk.app.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ext.getDfFromToday
import com.apx6.chipmunk.app.ext.getTodayMillis
import com.apx6.chipmunk.app.ext.getWeekMillis
import com.apx6.chipmunk.app.fcm.FcmHelper
import com.apx6.domain.dto.CmdNotification
import com.apx6.domain.repository.CheckListRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@HiltWorker
class ReminderWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val checkListRepository: CheckListRepository,
    val fcmHelper: FcmHelper,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        CoroutineScope(ioDispatcher).launch {

            val tomorrowMillis = getWeekMillis(1)
            val weekMillis = getWeekMillis(7)

            val cl = checkListRepository.getCheckListInWeek(tomorrowMillis, weekMillis)

            val dfTomorrow = cl.filter {
                val df = it.endDate.getDfFromToday()
                df == 1
            }

            val dfWeek = cl.filter {
                val df = it.endDate.getDfFromToday()
                df > 1
            }

            val tomorrowCount = dfTomorrow.size
            val weekCount = dfWeek.size

            val (comingCount, endDay) = if (tomorrowCount > 0) {
                tomorrowCount to 1
            } else {
                weekCount to 1
            }

            fcmHelper.sendNotification(
                CmdNotification(
                    onComingCount = comingCount,
                    endDay = endDay
                )
            )
        }

        return Result.success()
    }

}