package com.bhaveshp750.mynotification.allstyles

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == "custom_notification")
            Toast.makeText(context, "Image Clicked", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Hello World!", Toast.LENGTH_SHORT).show()
    }
}