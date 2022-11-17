package com.bhaveshp750.mynotification.allstyles

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.MediaMetadata
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import com.bhaveshp750.mynotification.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyNotificationService(private val context: Context) {
    private val notificationManager = NotificationManagerCompat.from(context)

    fun sendOnChannel1(title: String, message: String) {
        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_1_ID)) {
            openChannelSettings(CHANNEL_1_ID)
            return
        }

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
        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_3_ID)) {
            openChannelSettings(CHANNEL_3_ID)
            return
        }
        val notification = NotificationCompat.Builder(context, CHANNEL_3_ID)
            .setSmallIcon(R.drawable.ic_2)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        notificationManager.notify(2, notification.build())
    }

    fun notifyWithButton(title: String, message: String) {
        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_1_ID)) {
            openChannelSettings(CHANNEL_1_ID)
            return
        }

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

        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_1_ID)) {
            openChannelSettings(CHANNEL_1_ID)
            return
        }
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
        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_2_ID)) {
            openChannelSettings(CHANNEL_2_ID)
            return
        }
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
        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_1_ID)) {
            openChannelSettings(CHANNEL_1_ID)
            return
        }
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
        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_2_ID)) {
            openChannelSettings(CHANNEL_2_ID)
            return
        }
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
        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_1_ID)) {
            openChannelSettings(CHANNEL_1_ID)
            return
        }
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
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

    fun notifyProgressBar() {
        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_2_ID)) {
            openChannelSettings(CHANNEL_2_ID)
            return
        }
        val progressMax = 100

        val notification = NotificationCompat.Builder(context, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_2)
            .setContentTitle("Download [Fake]")
            .setContentText("Download in progress [Fake]")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setProgress(progressMax, 0, false)

        notificationManager.notify(2, notification.build())
        GlobalScope.launch {
            delay(2000)
            for (i in 0..9){
                notification.setProgress(progressMax, i*10, false)
                notificationManager.notify(12, notification.build())
                delay(1000)
            }

            notification.setContentText("Download finished")
                .setOngoing(false)
                .setProgress(0, 0, false)

            notificationManager.notify(12, notification.build())
        }

    }

    fun notifyNotificationGroups() {
        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_2_ID)) {
            openChannelSettings(CHANNEL_2_ID)
            return
        }
        val notification = NotificationCompat.Builder(context, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_2)
            .setContentTitle("Title")
            .setContentText("Message 0")
            .setPriority(NotificationCompat.PRIORITY_LOW)

        notificationManager.notify(13, notification.build())

        GlobalScope.launch {
            delay(1000)
            for (i in 1..10){
                notification.setContentText("Message $i")
                notificationManager.notify(13+i, notification.build())
                delay(1000)
            }
        }
    }

    fun notifyNotificationManualGrouping() {
        if(!notificationManager.areNotificationsEnabled()){
            openNotificationSettings()
            return
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            isChannelBlocked(CHANNEL_2_ID)) {
            openChannelSettings(CHANNEL_2_ID)
            return
        }
        val title1 = "Title 1"
        val message1 = "Message 1"
        val title2 = "Title 2"
        val message2 = "Message 2"

        val notification1 = NotificationCompat.Builder(context, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_2)
            .setContentTitle(title1)
            .setContentText(message1)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setGroup("example_group")
            .build()

        val notification2 = NotificationCompat.Builder(context, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_2)
            .setContentTitle(title2)
            .setContentText(message2)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setGroup("example_group")
            .build()

        val summaryNotification = NotificationCompat.Builder(context, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_2)
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine("$title1 $message1")
                    .addLine("$title2 $message2")
                    .setBigContentTitle("2 new messages")
                    .setSummaryText("user@mail.com")
            )
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setGroup("example_group")
            .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN)
            .setGroupSummary(true)
            .build()
        GlobalScope.launch {
            delay(1000)
            notificationManager.notify(24, notification1)
            delay(1000)
            notificationManager.notify(25, notification2)
            delay(1000)
            notificationManager.notify(26, summaryNotification)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isChannelBlocked(channelId: String): Boolean {
        val channel = notificationManager.getNotificationChannel(channelId) ?: return false

        var bool = channel.importance == NotificationManager.IMPORTANCE_NONE

        val group = notificationManager.getNotificationChannelGroup(channel.group)
        if(group != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            bool = group.isBlocked
        }

        return bool
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun openChannelSettings(channelId: String){
        val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId)
        context.startActivity(intent)
    }

    private fun openNotificationSettings() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            context.startActivity(intent)
        }else{
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:${context.packageName}")
            context.startActivity(intent)
        }
    }

    fun deleteNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.deleteNotificationChannel(CHANNEL_3_ID)
        }
    }

    fun notifyCustomNotification() {
        val collapsedViews = RemoteViews(context.packageName, R.layout.notificaion_collapsed)

        val expandedView = RemoteViews(context.packageName, R.layout.notificaion_expanded)

        collapsedViews.setTextViewText(R.id.text_view_collapsed_text, "Hello world")
        expandedView.setImageViewResource(R.id.image_view_expanded, R.drawable.swami_17)

        val intent = Intent(context, NotificationReceiver::class.java)
        intent.action = "custom_notification"
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
        )
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded, pendingIntent)

        val notification = NotificationCompat.Builder(context, CHANNEL_5_ID)
            .setSmallIcon(R.drawable.ic_assistant)
            .setContentTitle("Title")
            .setContentText("This is a default Notification")
            .setCustomContentView(collapsedViews)
            .setCustomBigContentView(expandedView)
//            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()

        notificationManager.notify(27, notification)
    }
}