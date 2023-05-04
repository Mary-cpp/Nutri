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

    private val tag = this::class.java.simpleName

    private fun setIntentData(notification: NotificationType){
        val contextWrapper = ContextWrapper(context)
        alarmIntent.apply {
            context
            action = context.getString(R.string.notification_action)
            putExtra("notification_title", contextWrapper.resources.getString(notification.title))
            putExtra("notification_description", contextWrapper.resources.getString(notification.description))
        }
        val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        notification.intent = pendingIntent
    }

    fun enableAlarms(){
        NotificationType.values().forEach { notification ->
            setIntentData(notification)
            var interval = AlarmManager.INTERVAL_DAY
            if (notification == NotificationType.WATER){
                interval = AlarmManager.INTERVAL_HOUR
            }
            alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                getTimeInMillis(notification.triggerTimeHours, notification.triggerTimeMinutes),
                interval,
                notification.intent
            ).apply {
                Log.w("ALARM MANAGER", "Enabled ${notification.text} alarm")
            }
        }
    }

    fun enableMealAlarms(){
        NotificationType.values().forEach { notification ->
            if (notification == NotificationType.WATER) return@forEach
            setIntentData(notification)
            alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                getTimeInMillis(notification.triggerTimeHours, notification.triggerTimeMinutes),
                AlarmManager.INTERVAL_DAY,
                notification.intent
            ).apply {
                Log.w("ALARM MANAGER", "Enabled ${notification.text} alarm")
            }
        }
    }

    fun setAlarm(
        notification : NotificationType,
    ){
        cancelAlarm(notification)
        var interval = AlarmManager.INTERVAL_DAY
        if (notification == NotificationType.WATER){
            interval = AlarmManager.INTERVAL_HOUR
        }
        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            getTimeInMillis(notification.triggerTimeHours, notification.triggerTimeMinutes),
            interval,
            notification.intent
        )
        Log.i(tag, "Changed ${notification.text} notification time on ${notification.triggerTimeHours}")
    }

    fun cancelAlarm(notification : NotificationType){
        setIntentData(notification)
        alarmManager.cancel(notification.intent)
    }

    fun cancelAllMealAlarms()
    = NotificationType.values().forEach {
        if (it == NotificationType.WATER) return@forEach
        cancelAlarm(it)
    }
}

fun getTimeInMillis(hours: Int, minutes: Int) : Long{
    return (hours * 3600 * 1000 + minutes * 60 * 1000).toLong()
}

enum class NotificationType(
    val text: String,
    val title: Int,
    val description: Int,
    var intent: PendingIntent? = null,
    var triggerTimeHours: Int,
    var triggerTimeMinutes: Int = 0,
){
    BREAKFAST(
        text = "Breakfast",
        title = R.string.breakfast_notification_title,
        description = R.string.breakfast_notification_description,
        triggerTimeHours = 9,
    ),
    LUNCH(
        text = "Lunch",
        title = R.string.lunch_notification_title,
        description = R.string.lunch_notification_description,
        triggerTimeHours = 14,
    ),
    DINNER(
        text = "Dinner",
        title = R.string.dinner_notification_title,
        description = R.string.dinner_notification_description,
        triggerTimeHours = 18
    ),
    BRUNCH(
        text = "Brunch",
        title = R.string.brunch_notification_title,
        description = R.string.brunch_notification_description,
        triggerTimeHours = 12,
    ),
    WATER(
        text = "Water",
        title = R.string.water_notification_title,
        description = R.string.water_notification_description,
        triggerTimeHours = 9
    )
}