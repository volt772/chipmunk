package com.apx6.chipmunk.app.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FcmService : FirebaseMessagingService() {

//    @Inject lateinit var mpdError: MpdError
//    @Inject lateinit var fcmHelper: FcmHelper
//    @Inject lateinit var mpPreference: MpPreference
//    @Inject lateinit var mpdParseUtils: MpdParseUtils
//
//    private val serviceScope = CoroutineScope(Dispatchers.Main)
//
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        val isNotificationAvailable = mpPreference.getBoolean(MpConstants.Prefs.NOTIFICATION_SET, false)
//
//        if (isNotificationAvailable) {
//            remoteMessage.data.let { _msg ->
//
//                val prm = parsingRemoteMessage(_msg)
//
//                prm?.let { _prm ->
//                    /* Argument 없으면 수행 안함*/
//                    if (_prm.args.isEmpty()) return
//
//                    serviceScope.launch {
//                        try {
//                            fcmHelper.sendNotification(
//                                id = Random.nextInt(),
//                                notificationDto = makeNotificationInfo(_prm),
//                                response = _prm
//                            )
//                        } catch (e: Exception) {
//                            MpLogger.e(TAG, -1, "[Error] Service FCM : %s", e)
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 문구생성
//     */
//    private fun makeNotificationInfo(
//        data: MpdFcmResponse
//    ): MpdFcmNotificationDto {
//
//        try {
//            val notificationTemplate = MpdFcmNotification.notification(data.kind)
//
//            val arg = data.args
//            val msg = when (notificationTemplate.substituteCount) {
//                1 -> getString(notificationTemplate.fcmSentence, arg[0])
//                2 -> getString(notificationTemplate.fcmSentence, arg[0], arg[1])
//                3 -> getString(notificationTemplate.fcmSentence, arg[0], arg[1], arg[2])
//                else -> ""
//            }
//
//            return MpdFcmNotificationDto(
//                msg = msg,
//                destIntent = notificationTemplate.destIntent,
//                title = data.executor
//            )
//        } catch (e: Exception) {
//            throw mpdError.error(MpdErrorCode.FCM_DATA_PARSING_ERROR)
//        }
//    }
//
//    /**
//     * parsedData
//     * @desc string to list
//     */
//    private fun parsingRemoteMessage(
//        data: Map<String, String>?
//    ): MpdFcmResponse? {
//
//        return data?.let { _data ->
//            val fIds = parseIds(_data[MpConstants.FCM.IDS])
//            val fArgs = parseArgs(_data[MpConstants.FCM.ARGS] ?: "")
//            val fKind = _data[MpConstants.FCM.KIND] ?: ""
//            val fLink = _data[MpConstants.FCM.LINK] ?: ""
//            val fExecutor = _data[MpConstants.FCM.EXECUTOR] ?: ""
//
//            MpdFcmResponse(
//                ids = fIds,
//                args = fArgs,
//                kind = fKind,
//                link = fLink,
//                executor = fExecutor
//            )
//        } ?: run {
//            null
//        }
//    }
//
//    /**
//     * Ids Parsing
//     * @desc Dict to MpdFcmIds
//     */
//    private fun parseIds(
//        ids: String?
//    ) = mpdParseUtils.toFcmIds(ids)
//
//    /**
//     * Args Parsing
//     * @desc String to List
//     */
//    private fun parseArgs(
//        args: String
//    ) = try {
//        args.split(ARG_DELIMITER)
//    } catch (e: Exception) {
//        emptyList()
//    }
//
//    companion object {
//
//        private const val TAG = "FCM Service"
//
//        private const val ARG_DELIMITER = "||"
//    }
}
