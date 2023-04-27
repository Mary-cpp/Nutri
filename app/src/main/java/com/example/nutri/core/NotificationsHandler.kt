package com.example.nutri.core

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.util.Log
import com.example.nutri.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationsHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val alarmManager: AlarmManager by lazy { context.getSystemService(Context.ALARM_SERVICE) as AlarmManager}
    private val alarmIntent : Intent by lazy { Intent(context, AlarmReceiver::class.java) }

    fun setDefaultAlarms(){
        val contextWrapper = ContextWrapper(context)

        NotificationType.values().forEach { notification ->
            alarmIntent.apply {
                context
                action = context.getString(R.string.notification_action)
                putExtra("notification_title", contextWrapper.resources.getString(notification.title))
                putExtra("notification_description", contextWrapper.getString(notification.description))
            }

            val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE)
            notification.intent = pendingIntent

            alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                notification.triggerTimeInMillis,
                AlarmManager.INTERVAL_DAY,
                notification.intent
            ).apply {
                Log.w("ALARM MANAGER", "Enabled ${notification.text} alarm")
            }
        }
    }

    fun setAlarm(timeInMillis : Long, notification : NotificationType){
        cancelAlarm(notification)
        notification.triggerTimeInMillis = timeInMillis
        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            notification.triggerTimeInMillis,
            1800*1000,
            notification.intent
        )
    }

    fun cancelAlarm(notification : NotificationType)
    = alarmManager.cancel(notification.intent)

    fun cancelAllMealAlarms()
    = NotificationType.values().forEach {
        if (it == NotificationType.WATER) return@forEach
        alarmManager.cancel(it.intent)
    }
}

enum class NotificationType(
    val text: String,
    val title: Int,
    val description: Int,
    var triggerTimeInMillis: Long,
    var intent: PendingIntent? = null
){
    BREAKFAST(
        text = "Breakfast",
        title = R.string.breakfast_notification_title,
        description = R.string.breakfast_notification_description,
        triggerTimeInMillis = 9*3600*1000
    ),
    LUNCH(
        text = "Lunch",
        title = R.string.lunch_notification_title,
        description = R.string.lunch_notification_description,
        triggerTimeInMillis = 14*3600*1000
    ),
    DINNER(
        text = "Dinner",
        title = R.string.dinner_notification_title,
        description = R.string.dinner_notification_description,
        triggerTimeInMillis = 18*3600*1000
    ),
    BRUNCH(
        text = "Brunch",
        title = R.string.brunch_notification_title,
        description = R.string.brunch_notification_description,
        triggerTimeInMillis = 12*3600*1000
    ),
    WATER(
        text = "Water",
        title = R.string.water_notification_title,
        description = R.string.water_notification_description,
        triggerTimeInMillis = 9*3600*1000
    )
}