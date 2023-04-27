package com.example.nutri.ui.screens.configs

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
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

@HiltViewModel
class NotificationsConfigViewModel @Inject constructor(
    @ApplicationContext context: Context,
    navControllerHolder: NavControllerHolder,
) : NavigationViewModel(navControllerHolder), DefaultLifecycleObserver {

    private val notificationsHandler : NotificationsHandler by lazy {
        NotificationsHandler(context = context)
    }

    var breakfastNotificationTime : MutableState<Pair<Int,Int>> = mutableStateOf(Pair(0,0))
    var lunchNotificationTime : MutableState<Pair<Int,Int>> = mutableStateOf(Pair(0,0))
    var dinnerNotificationTime : MutableState<Pair<Int,Int>> = mutableStateOf(Pair(0,0))
    var brunchNotificationTime : MutableState<Pair<Int,Int>> = mutableStateOf(Pair(0,0))

    private val sp : SharedPreferences by lazy{ context.getSharedPreferences(NUTRI_PREFERENCES, Context.MODE_PRIVATE)}

    override fun onResume(owner: LifecycleOwner) {
        onNotificationsPageLoaded()
    }

    private fun onNotificationsPageLoaded(){
        breakfastNotificationTime.value = Pair(
            sp.getInt("breakfast_hours", 9), sp.getInt("breakfast_minutes", 0))
        lunchNotificationTime.value = Pair(
            sp.getInt("lunch_hours", 13), sp.getInt("lunch_minutes", 0))
        dinnerNotificationTime.value = Pair(
            sp.getInt("dinner_hours", 19), sp.getInt("dinner_minutes", 0))
        brunchNotificationTime.value = Pair(
            sp.getInt("brunch_hours", 16), sp.getInt("brunch_minutes", 0))
    }

    fun onMealNotificationTimeChanged(notificationType: NotificationType){
        when (notificationType){
            NotificationType.BREAKFAST -> {
                sp.edit()
                    .putInt("breakfast_hours", breakfastNotificationTime.value.first)
                    .putInt("breakfast_minutes", breakfastNotificationTime.value.second)
                    .apply()
            }
            NotificationType.LUNCH -> {
                sp.edit()
                    .putInt("lunch_hours", lunchNotificationTime.value.first)
                    .putInt("lunch_minutes", lunchNotificationTime.value.second)
                    .apply()
            }
            NotificationType.DINNER -> {
                sp.edit()
                    .putInt("dinner_hours", dinnerNotificationTime.value.first)
                    .putInt("dinner_minutes", dinnerNotificationTime.value.second)
                    .apply()
            }
            NotificationType.BRUNCH -> {
                sp.edit()
                    .putInt("brunch_hours", brunchNotificationTime.value.first)
                    .putInt("brunch_minutes", brunchNotificationTime.value.second)
                    .apply()
            }
            else -> {}
        }
    }

    fun onMealNotificationSwitchStateChanged(state: Boolean){
        when(state){
            true -> notificationsHandler.setDefaultAlarms() // Change!!!!
            false -> notificationsHandler.cancelAllMealAlarms()
        }
    }

    fun onWaterNotificationsSwitchStateChanged(state : Boolean){
        when (state){
            true -> notificationsHandler.setAlarm((9*3600*1000).toLong(), NotificationType.WATER)
            false -> notificationsHandler.cancelAlarm(NotificationType.WATER)
        }
    }
}