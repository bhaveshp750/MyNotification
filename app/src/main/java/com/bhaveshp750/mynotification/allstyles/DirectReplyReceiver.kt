package com.bhaveshp750.mynotification.allstyles

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.RemoteInput
import androidx.core.graphics.drawable.IconCompat
import com.bhaveshp750.mynotification.R

class DirectReplyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val remoteInput: Bundle = RemoteInput.getResultsFromIntent(intent)
        val myNotificationService = MyNotificationService(context)

        val mIcon = IconCompat.createWithResource(context, R.drawable.lord_simandhar_swami)
        val replyText = remoteInput.getCharSequence("key_text_reply").toString()
        messages.add(Message(text = replyText, sender = "Me", icon = mIcon))
        myNotificationService.notifyMessagingStyle()
    }
}