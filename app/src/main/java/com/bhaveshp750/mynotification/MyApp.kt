package com.bhaveshp750.mynotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build

const val CHANNEL_ID = "my_channel_id_2"
const val CHANNEL_NAME = "My Channel Name 2"

const val COUNTER_CHANNEL_ID = "counter_channel_id"
const val COUNTER_CHANNEL_NAME = "For Counter"

/* ------- all style -------- */
const val CHANNEL_1_ID = "channel1"
const val CHANNEL_1_NAME = "Channel 1"
const val CHANNEL_2_ID = "channel2"
const val CHANNEL_2_NAME = "Channel 2"

const val GROUP_1_ID = "group1"
const val GROUP_2_ID = "group2"

const val CHANNEL_3_ID = "channel3"
const val CHANNEL_3_NAME = "Channel 3"
const val CHANNEL_4_ID = "channel4"
const val CHANNEL_4_NAME = "Channel 4"
const val CHANNEL_5_ID = "channel5"
const val CHANNEL_5_NAME = "Channel 5"

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

            /* - all style - */

            val group1 = NotificationChannelGroup(
                GROUP_1_ID,
                "Group 1"
            )

            val group2 = NotificationChannelGroup(
                GROUP_2_ID,
                "Group 2"
            )


            val channel1 = NotificationChannel(
                CHANNEL_1_ID,
                CHANNEL_1_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.BLUE
                enableLights(true)
                description = "This is Channel 1"
            }
            channel1.group = GROUP_1_ID

            val channel2 = NotificationChannel(
                CHANNEL_2_ID,
                CHANNEL_2_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                lightColor = Color.YELLOW
                enableLights(true)
                description = "This is Channel 2"
            }
            channel2.group = GROUP_1_ID


            val channel3 = NotificationChannel(
                CHANNEL_3_ID,
                CHANNEL_3_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.BLUE
                enableLights(true)
                description = "This is Channel 3"
            }
            channel3.group = GROUP_2_ID

            val channel4 = NotificationChannel(
                CHANNEL_4_ID,
                CHANNEL_4_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                lightColor = Color.YELLOW
                enableLights(true)
                description = "This is Channel 4"
            }

            val channel5 = NotificationChannel(
                CHANNEL_5_ID,
                CHANNEL_5_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.YELLOW
                enableLights(true)
                description = "This is Channel 5"
            }
            channel5.group = GROUP_2_ID

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            manager.createNotificationChannel(counterChannel)

            /* - all style - */
            manager.createNotificationChannelGroup(group1)
            manager.createNotificationChannelGroup(group2)
            manager.createNotificationChannel(channel1)
            manager.createNotificationChannel(channel2)
            manager.createNotificationChannel(channel3)
            manager.createNotificationChannel(channel4)
            manager.createNotificationChannel(channel5)
        }
    }
}