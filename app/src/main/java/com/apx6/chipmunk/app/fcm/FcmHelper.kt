package com.apx6.chipmunk.app.fcm

interface FcmHelper {

    val fcmToken: String
//
//    fun sendNotification(
//        id: Int,
//        notificationDto: MpdFcmNotificationDto,
//        response: MpdFcmResponse
//    )
//
    fun setFcmToken()
}
