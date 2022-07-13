package com.apx6.chipmunk.app.fcm

import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class FcmHelperImpl @Inject constructor(
//    private val context: Context,
//    private val spacesDao: SpacesDao,
//    private val badgeCountHelper: BadgeCountHelper,
//    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : FcmHelper {

    private var fToken: String = ""

    override val fcmToken: String
        get() {
            return fToken
        }
//
//    @SuppressLint("InvalidWakeLockTag")
//    override fun sendNotification(
//        id: Int,
//        notificationDto: MpdFcmNotificationDto,
//        response: MpdFcmResponse
//    ) {
//
//        /* 제목(실행자)*/
//        val title = notificationDto.title
//
//        /* 안내문구*/
//        val msg = notificationDto.msg
//
//        CoroutineScope(ioDispatcher).launch {
//            val ids = response.ids
//
//            val spaceId = ids.spaceId ?: 0L
//            val boardId = ids.boardId ?: 0L
//            val sectionId = ids.sectionId ?: 0L
//            val cardId = ids.cardId ?: 0L
//            val logId = ids.commentId ?: 0L
//            val labelId = 0L // 의미 없음 (dummy)
//
//            /* Room Row ID Query*/
//            val rowIds = spacesDao.getAllRowIds(spaceId, boardId, sectionId, cardId, logId, labelId)
//
//            /* 알림포기 : 삭제류외 AND 공간 ID가 0인경우*/
//            if ((notificationDto.destIntent != MpdFcmDestination.DASHBOARD) && (rowIds.spaceId == 0L || spaceId == 0L)) {
//                return@launch
//            }
//
//            /* MpIds*/
//            val mpIds = MpIds(
//                spaceRowId = rowIds.spaceId,
//                spaceId = spaceId,
//                boardRowId = rowIds.boardId,
//                boardId = boardId,
//                sectionRowId = rowIds.sectionId,
//                sectionId = sectionId,
//                cardRowId = rowIds.cardId,
//                cardId = cardId,
//                logRowId = rowIds.commentId,
//                logId = logId
//            )
//
//            /* destination */
//            val destination = when (notificationDto.destIntent) {
//                MpdFcmDestination.DASHBOARD -> MpConstants.FCM.PUSH_DASHBOARD
//                MpdFcmDestination.CARD_DETAIL -> MpConstants.FCM.PUSH_CARD_DETAIL
//                MpdFcmDestination.COMMENT_DETAIL -> MpConstants.FCM.PUSH_COMMENT_DETAIL
//                else -> MpConstants.FCM.PUSH_DASHBOARD
//            }
//
//            /* Pending Intent*/
//            val pi = Intent(context, DashBoardActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                putExtra(MpConstants.FCM.TASK_NOTIFICATION_ID, id)
//                putExtra(MpIntent.IDS.IDS, mpIds)
//                putExtra(MpIntent.FCM.PUSH_DESTINATION, destination)
//            }
//
//            val piIntent = PendingIntent.getActivity(
//                context, 0, pi, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//            )
//
//            /* Delete Intent*/
//            val di = Intent(context, FcmBroadcastReceiver::class.java)
//
//            val diIntent = PendingIntent.getBroadcast(
//                context, Random().nextInt(543254), di, PendingIntent.FLAG_IMMUTABLE
//            )
//
//            /* Channel ID, Group Name*/
//            val channelId = MpConstants.FCM.TASK_CHANNEL
//            val group = MpConstants.FCM.TASK_GROUP
//
//            /* Notification*/
//            val taskNotification = NotificationCompat.Builder(context, channelId)
//                .setSmallIcon(R.drawable.ic_fcm_small)
//                .setContentTitle(title)
//                .setContentText(msg)
//                .setStyle(textStyle)
//                .setGroup(group)
//                .setAutoCancel(true)
//                .setDeleteIntent(diIntent)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setContentIntent(piIntent)
//                .build()
//
//            /* Group Notification Builder*/
//            val groupTaskNotificationBuilder = NotificationCompat.Builder(context, channelId)
//                .setSmallIcon(R.drawable.ic_fcm_small)
//                .setContentTitle(title)
//                .setContentText(msg)
//                .setChannelId(channelId)
//                .setContentIntent(piIntent)
//                .setOnlyAlertOnce(true)
//                .setGroup(group)
//                .setAutoCancel(true)
//                .setGroupSummary(true)
//                .setDeleteIntent(diIntent)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .build()
//
//            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            val channel = NotificationChannel(channelId, MpConstants.FCM.MAILPLUG_TASK, NotificationManager.IMPORTANCE_HIGH)
//            channel.apply {
//                enableLights(true)
//                lightColor = ContextCompat.getColor(context, R.color.orange_h21)
//                setShowBadge(true)
//                enableVibration(true)
//            }
//
//            notificationManager.createNotificationChannel(channel)
//
//            /* Set Badge Count*/
//            setBadge()
//
//            /* Execute Notification*/
//            NotificationManagerCompat.from(context).apply {
//                notify(id, taskNotification)
//
//                group.indexOf("_").run {
//                    val groupId = group.substring(this + 1, group.length).toInt()
//                    notify(groupId, groupTaskNotificationBuilder)
//                }
//            }
//        }
//    }
//
//    /* BadgeCount*/
//    private fun setBadge() {
//        badgeCountHelper.badgeCount().run {
//            ShortcutBadger.applyCount(context, this)
//        }
//    }
//
//    /**
//     * 알림박스 스타일
//     */
//    private val textStyle: NotificationCompat.BigTextStyle
//        get() {
//            return NotificationCompat.BigTextStyle()
//        }
//
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
