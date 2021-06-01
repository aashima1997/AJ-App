package com.example.ajapp.Menu.BroadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.ajapp.Menu.ui.Adapter.Notification

class AlertReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //  This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val notificationUtils = Notification(context)
        val notification = notificationUtils.getNotificationBuilder().build()
        notificationUtils.getManager().notify(150, notification)

    }
}