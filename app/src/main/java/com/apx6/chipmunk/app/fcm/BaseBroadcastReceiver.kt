package com.apx6.chipmunk.app.fcm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.CallSuper

abstract class BaseBroadcastReceiver : BroadcastReceiver() {

    @CallSuper
    override fun onReceive(context: Context?, intent: Intent?) { }
}
