package com.apx6.chipmunk.app.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.CallSuper

abstract class CmBaseBroadcastReceiver: BroadcastReceiver() {

    @CallSuper
    override fun onReceive(context: Context?, intent: Intent?) { }

}