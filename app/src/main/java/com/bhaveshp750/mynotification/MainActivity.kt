package com.bhaveshp750.mynotification

import android.Manifest
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.bhaveshp750.mynotification.ui.theme.MyNotificationTheme
import com.bhaveshp750.mynotification.allstyles.MainActivity

private const val NOTIFICATION_ID = 12

class MainActivity : ComponentActivity() {
    private lateinit var  notification: Notification
    private lateinit var  notificationManagerCompat: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotificationTheme {
                val context = LocalContext.current
                var hasNotificationPermission by remember {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        mutableStateOf(
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.POST_NOTIFICATIONS
                            ) == PackageManager.PERMISSION_GRANTED
                        )
                    } else mutableStateOf(true)
                }

                val permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted ->
                        hasNotificationPermission = isGranted
                    }
                )

                val service = CounterNotificationService(this)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   Column(
                       modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       Button(onClick = {
                           startActivity(Intent(context, MainActivity::class.java))
                       }) {
                           Text(text = "All Style Notification")
                       }
                       Button(onClick = {
                           notificationManagerCompat.notify(NOTIFICATION_ID, notification)
                       }) {
                           Text(text = "Normal Notification")
                       }

                       Spacer(modifier = Modifier.height(8.dp))

                       Button(onClick = {
                           service.showCounterNotification(Counter.value)
                       }) {
                           Text(text = "Counter Notification")
                       }

                       Spacer(modifier = Modifier.height(8.dp))

                       Button(onClick = {
                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                               if(hasNotificationPermission) {
                                   showNotification()
                               }else {
                                   permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                               }
                           }else {
                               showNotification()
                           }


                       }) {
                           Text(text = "Notification with Permission")
                       }
                   }
                }
            }

            val intent = Intent(this, MainActivity::class.java)
            val pendingIntent = TaskStackBuilder.create(this).run {
                addNextIntentWithParentStack(intent)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getPendingIntent(12, PendingIntent.FLAG_IMMUTABLE)
                } else {
                    getPendingIntent(12, PendingIntent.FLAG_UPDATE_CURRENT)
                }
            }

            notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Some Notification")
                .setContentText("This is the content text")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()

            notificationManagerCompat = NotificationManagerCompat.from(this)

        }
    }

    private fun showNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Hello World")
            .setContentText("This is the content text")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(121, notification)
    }


}



@Composable
fun Greeting() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyNotificationTheme {
        Greeting()
    }
}