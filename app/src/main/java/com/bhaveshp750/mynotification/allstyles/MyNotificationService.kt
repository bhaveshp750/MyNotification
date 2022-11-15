package com.bhaveshp750.mynotification.allstyles

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.MediaMetadata
import android.os.Build
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
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

    fun notifyBigPictureStyle(title: String, message: String){
        val activityIntent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            context,
            0,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val picture = BitmapFactory.decodeResource(context.resources, R.drawable.swami_17)

        val notification = NotificationCompat.Builder(context, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_one)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(picture)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(picture)
                    .bigLargeIcon(null)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(contentIntent)
            .build()

        notificationManager.notify(7, notification)
    }

    fun notifyMediaStyle(title: String = "Track 1", message: String = "Song content..") {
        val mediaSessionCompat  = MediaSessionCompat(context, "MediaNotification")
        mediaSessionCompat.setMetadata(
            MediaMetadataCompat.Builder()
                .putString(MediaMetadata.METADATA_KEY_DISPLAY_TITLE, "Song Title")
                .putString(MediaMetadata.METADATA_KEY_ARTIST, "Artist Dadabhagwan")
                .build()
        )

        val largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.dada_niruma_pujyashree)

        val notification = NotificationCompat.Builder(context, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_2)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(largeIcon)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0,1,2)
                    .setMediaSession(mediaSessionCompat.sessionToken)
            )
            .setSubText("Sub Text")
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_PROGRESS)
            .addAction(R.drawable.ic_previous, "Previous", null)
            .addAction(R.drawable.ic_pause, "Pause", null)
            .addAction(R.drawable.ic_next, "Next", null)
            .addAction(R.drawable.ic_star_outline, "Favorite", null)

        notificationManager.notify(9, notification.build())
    }


    fun notifyMessagingStyle(){
        val activityIntent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            context,
            0,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val remoteInput = RemoteInput.Builder("key_text_reply")
            .setLabel("Your answer...")
            .build()

        val replyIntent = Intent(context, DirectReplyReceiver::class.java)

        var flag = PendingIntent.FLAG_UPDATE_CURRENT
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            flag = PendingIntent.FLAG_MUTABLE

        val replyPendingIntent = PendingIntent.getBroadcast(
            context,
            12112,
            replyIntent,
            flag
        )

        val replyAction = NotificationCompat.Action.Builder(
            R.drawable.ic_send,
            "Reply",
            replyPendingIntent
        ).addRemoteInput(remoteInput).build()

        val mBuilder = Person.Builder().setName("Me").setBot(false).setImportant(true).build()

        val messagingStyle = NotificationCompat.MessagingStyle(mBuilder)
        messagingStyle.conversationTitle = "Group Chat"

        messages.forEach { message ->
            val mMessage = NotificationCompat.MessagingStyle.Message(
                message.text,
                message.timestamp,
                Person.Builder().setName(
                    message.sender).setIcon(message.icon).setBot(false).setImportant(true).build()
            )
            messagingStyle.addMessage(mMessage)
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_one)
            .setStyle(messagingStyle)
            .setColor(Color.BLUE)
            .setOnlyAlertOnce(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(contentIntent)
            .setContentTitle("Title")
            .setContentText("Text")

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            notification.addAction(replyAction)

        notificationManager.notify(10, notification.build())
    }

}