package com.apx6.chipmunk.app.fcm

import com.apx6.domain.dto.CmdNotification

interface FcmHelper {

    val fcmToken: String

    fun sendNotification(nt: CmdNotification)

    fun setFcmToken()
}
