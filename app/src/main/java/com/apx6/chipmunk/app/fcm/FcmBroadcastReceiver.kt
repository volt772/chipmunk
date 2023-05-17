package com.apx6.chipmunk.app.fcm

import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FcmBroadcastReceiver : BaseBroadcastReceiver() {

    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {
        super.onReceive(context, intent)
    }
}
