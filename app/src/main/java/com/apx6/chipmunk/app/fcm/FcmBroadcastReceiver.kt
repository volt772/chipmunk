package com.apx6.chipmunk.app.fcm

import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FcmBroadcastReceiver : BaseBroadcastReceiver() {

    @Inject
    lateinit var badgeCountHelper: BadgeCountHelper

    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {
        super.onReceive(context, intent)

        intent?.let {
            badgeCountHelper.clear()
        }
    }
}
