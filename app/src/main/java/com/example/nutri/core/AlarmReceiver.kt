package com.example.nutri.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.nutri.MainActivity
import com.example.nutri.R

class AlarmReceiver : BroadcastReceiver() {

    private val channelId = "0"
    private val tag = this::class.simpleName

    override fun onReceive(p0: Context?, p1: Intent?) {
        p1?.let { intent ->
            if (p0 != null) {

                Log.i(tag, "Received intent")

                NotificationManagerCompat.from(p0).areNotificationsEnabled().apply {
                    Log.i(tag, "Notifications enabled: $this")
                }
                notifyUser(p0, intent)
            }
        }
    }

    private fun notifyUser(context: Context, intent: Intent){

        val title = intent.getStringExtra("notification_title")
        val description = intent.getStringExtra("notification_description")

        createNotificationChannel(context)

        val notificationIntent = Intent(context, MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(
            context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        with(NotificationManagerCompat.from(context)){
            notify(System.currentTimeMillis().toInt(), createNotification(
                title = title!!,
                details = description!!,
                context = context,
                notificationIntent = notificationPendingIntent,
            ))
        }
    }

    private fun createNotification(
        title: String,
        details: String,
        context: Context,
        notificationIntent: PendingIntent
    )
    = NotificationCompat.Builder(context, channelId)
        .setContentTitle(title)
        .setContentText(details)
        .setSmallIcon(R.drawable.home48px)
        .setContentIntent(notificationIntent)
        .build()

    private fun createNotificationChannel(context: Context){
        val name = context.resources.getString(R.string.notification_channel_name)
        val descriptionText = context.resources.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(channelId, name, importance).apply { description = descriptionText }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}