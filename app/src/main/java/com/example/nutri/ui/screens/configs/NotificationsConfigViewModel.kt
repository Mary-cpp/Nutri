package com.example.nutri.ui.screens.configs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.nutri.core.NotificationType
import com.example.nutri.core.NotificationsHandler
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.navigation.NavigationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val NUTRI_PREFERENCES = "NutriPreferences"
const val mealNotifIndicator = "areMealNotificationsEnabled"
const val waterNotifIndicator = "areWaterNotificationsEnabled"

@HiltViewModel
class NotificationsConfigViewModel @Inject constructor(
    @ApplicationContext context: Context,
    navControllerHolder: NavControllerHolder,
) : NavigationViewModel(navControllerHolder), DefaultLifecycleObserver {

    private val notificationsHandler : NotificationsHandler by lazy {
        NotificationsHandler(context = context)
    }

    val areMealNotificationsEnabled = mutableStateOf(true)
    val areWaterNotificationsEnabled = mutableStateOf(true)

    private val tag = this::class.simpleName
    private val sp : SharedPreferences by lazy{ context.getSharedPreferences(NUTRI_PREFERENCES, Context.MODE_PRIVATE)}

    override fun onResume(owner: LifecycleOwner) {
        onNotificationsPageLoaded()
    }

    private fun onNotificationsPageLoaded(){
        Log.i(tag, "Notifications page loaded")
        areMealNotificationsEnabled.value = sp.getBoolean(mealNotifIndicator, true)
        areWaterNotificationsEnabled.value = sp.getBoolean(waterNotifIndicator, true)
        NotificationType.values().forEach {
            if (it == NotificationType.WATER) return
            if (!sp.contains("${it.name}_hours") || !sp.contains("${it.name}_minutes")) return
            it.triggerTimeHours = sp.getInt("${it.name}_hours", 0)
            it.triggerTimeMinutes = sp.getInt("${it.name}_minutes", 0)
            Log.i(tag, "${it.triggerTimeHours}:${it.triggerTimeMinutes}")
        }
    }

    fun onMealNotificationTimeChanged(
        notificationType: NotificationType,
        hours: Int,
        minutes: Int,
    ){
        notificationType.triggerTimeHours = hours; notificationType.triggerTimeMinutes = minutes
        Log.i(tag, "Changed ${notificationType.text} notification time on ${notificationType.triggerTimeHours}")
        notificationsHandler.setAlarm(notificationType)
        sp.edit()
            .putInt("${notificationType.name}_hours", notificationType.triggerTimeHours)
            .putInt("${notificationType.name}_minutes", notificationType.triggerTimeMinutes)
            .apply()
    }

    fun onMealNotificationSwitchStateChanged(state: Boolean){
        areMealNotificationsEnabled.value = state
        sp.edit().putBoolean(mealNotifIndicator, state).apply()
        when(state){
            true -> { notificationsHandler.enableMealAlarms();Log.i(tag, "Enabled MEAL notifications") }
            false -> {notificationsHandler.cancelAllMealAlarms(); Log.i(tag, "Disabled MEAL notifications")}
        }
    }

    fun onWaterNotificationsSwitchStateChanged(state : Boolean){
        areWaterNotificationsEnabled.value = state
        sp.edit().putBoolean(waterNotifIndicator, state).apply()
        when (state){
            true -> {notificationsHandler.setAlarm(NotificationType.WATER); Log.i(tag, "Enabled WATER notifications")}
            false -> {notificationsHandler.cancelAlarm(NotificationType.WATER); Log.i(tag, "Disabled WATER notifications")}
        }
    }
}