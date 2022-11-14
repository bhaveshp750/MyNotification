package com.bhaveshp750.mynotification.allstyles

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, p1: Intent) {
        Toast.makeText(context, "Hello World!", Toast.LENGTH_SHORT).show()
    }
}