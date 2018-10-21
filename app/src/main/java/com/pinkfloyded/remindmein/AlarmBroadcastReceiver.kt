package com.pinkfloyded.remindmein

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity(Intent(context, AlarmActivity::class.java))
    }
}
