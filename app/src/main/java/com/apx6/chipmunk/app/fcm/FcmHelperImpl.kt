package com.apx6.chipmunk.app.fcm

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ui.activity.DashBoardActivity
import com.apx6.domain.constants.CmdConstants
import com.apx6.domain.dto.CmdNotification
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Random
import javax.inject.Inject


class FcmHelperImpl @Inject constructor(
    private val context: Context,
//    private val spacesDao: SpacesDao,
//    private val badgeCountHelper: BadgeCountHelper,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : FcmHelper {

    private var fToken: String = ""

    override val fcmToken: String
        get() {
            return fToken
        }

    @SuppressLint("MissingPermission")
    override fun sendNotification(
        nt: CmdNotification
    ) {

        val endDay = nt.endDay
        val comingCount = nt.onComingCount

        val msg = if (endDay == 7) {
            context.getString(R.string.notification_in_week, comingCount)
        } else {
            context.getString(R.string.notification_in_day, comingCount)
        }

        CoroutineScope(ioDispatcher).launch {

            /* Pending Intent*/
            val pi = Intent(context, DashBoardActivity::class.java)

            val piIntent = PendingIntent.getActivity(
                context, 0, pi, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            /* Delete Intent*/
            val di = Intent(context, FcmBroadcastReceiver::class.java)

            val diIntent = PendingIntent.getBroadcast(
                context, Random().nextInt(543254), di, PendingIntent.FLAG_IMMUTABLE
            )

            /* Channel ID, Group Name*/
            val channelId = CmdConstants.FCM.CHANNEL_ID
            val group = CmdConstants.FCM.GROUP_ID

            /* Notification*/
            val taskNotification = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.logo_app)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(msg)
                .setStyle(textStyle)
                .setGroup(group)
                .setAutoCancel(true)
                .setDeleteIntent(diIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(piIntent)
                .build()

            /* Group Notification Builder*/
            val groupTaskNotificationBuilder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.logo_app)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(msg)
                .setChannelId(channelId)
                .setContentIntent(piIntent)
                .setOnlyAlertOnce(true)
                .setGroup(group)
                .setAutoCancel(true)
                .setGroupSummary(true)
                .setDeleteIntent(diIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(channelId, CmdConstants.FCM.CHIPMUNK, NotificationManager.IMPORTANCE_HIGH)
            channel.apply {
                enableLights(true)
                lightColor = ContextCompat.getColor(context, R.color.material_amber_700)
                setShowBadge(true)
                enableVibration(true)
            }

            notificationManager.createNotificationChannel(channel)

            /* Set Badge Count*/
//            setBadge()

            /* Execute Notification*/
            NotificationManagerCompat.from(context).apply {
                notify(1, taskNotification)

                group.indexOf("_").run {
                    val groupId = group.substring(this + 1, group.length).toInt()
                    notify(groupId, groupTaskNotificationBuilder)
                }
            }
        }
    }


    /* BadgeCount*/
//    private fun setBadge() {
//        badgeCountHelper.badgeCount().run {
//            ShortcutBadger.applyCount(context, this)
//        }
//    }

    /**
     * 알림박스 스타일
     */
    private val textStyle: NotificationCompat.BigTextStyle
        get() {
            return NotificationCompat.BigTextStyle()
        }

    override fun setFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            val token = it.result ?: ""
            fToken = token
        }
    }

    companion object {
        const val TAG = "FCMHelperImpl"
    }
}
