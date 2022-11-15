package com.bhaveshp750.mynotification.allstyles

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bhaveshp750.mynotification.CHANNEL_1_ID
import com.bhaveshp750.mynotification.CHANNEL_2_ID
import com.bhaveshp750.mynotification.R

class MyNotificationService(private val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)

    fun sendOnChannel1(title: String, message: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_one)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .build()

        notificationManager.notify(1, notification)
    }

    fun sendOnChannel2(title: String, message: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_2)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        notificationManager.notify(2, notification.build())
    }

    fun notifyWithButton(title: String, message: String) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            context,
            0,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val broadcastIntent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            12,
            broadcastIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_one)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(contentIntent)
            .addAction(R.drawable.ic_launcher_foreground, "Toast", pendingIntent)
            .build()

        notificationManager.notify(1, notification)
    }

    fun notifyBigTextStyle(title: String, message: String){
        val activityIntent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            context,
            0,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val broadcastIntent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            12,
            broadcastIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.swami_17)

        val notification = NotificationCompat.Builder(context, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_one)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(largeIcon)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .setBigContentTitle("Big Content Title")
                    .setSummaryText("Summary Text")
                    .bigText(context.getString(R.string.swami_into_big))
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(contentIntent)
            .addAction(R.drawable.ic_launcher_foreground, "Toast", pendingIntent)
            .build()

        notificationManager.notify(3, notification)
    }

    fun notifyInboxStyle(title: String, message: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_2)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine("This is line 1")
                    .addLine("This is line 2")
                    .addLine("This is line 3")
                    .addLine("This is line 4")
                    .addLine("This is line 5")
                    .addLine("This is line 6")
                    .addLine("This is line 7")
                    .addLine("This is line 8")
                    .setBigContentTitle("Big Content Title")
                    .setSummaryText("Summary Text")
            )
            .setPriority(NotificationCompat.PRIORITY_LOW)

        notificationManager.notify(4, notification.build())
    }
}