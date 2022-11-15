package com.bhaveshp750.mynotification.allstyles

import androidx.core.graphics.drawable.IconCompat

data class Message(
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val sender: String?,
    val icon: IconCompat?
)


val messages = mutableListOf<Message>(
    Message(text = "Good Morning", sender = "Ram", icon = null),
    Message(text = "Hello, Ssup?", sender = null, icon = null),
    Message(text = "GM, Hi", sender = "Suresh", icon = null)
)