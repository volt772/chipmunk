package com.apx6.chipmunk.app.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FcmService : FirebaseMessagingService() {

//    @Inject lateinit var mpdError: MpdError
    @Inject
    lateinit var fcmHelper: FcmHelper

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        fcmHelper.sendNotification(CmdNotification.default())
    }
}
