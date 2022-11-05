package com.bhaveshp750.mynotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build

const val CHANNEL_ID = "my_channel_id_2"
const val CHANNEL_NAME = "My Channel Name 2"

const val COUNTER_CHANNEL_ID = "counter_channel_id"
const val COUNTER_CHANNEL_NAME = "For Counter"

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }

            val counterChannel = NotificationChannel(
                COUNTER_CHANNEL_ID,
                COUNTER_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.RED
                enableLights(true)
                description = "Used for the increment counter notifications"
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            manager.createNotificationChannel(counterChannel)
        }
    }
}