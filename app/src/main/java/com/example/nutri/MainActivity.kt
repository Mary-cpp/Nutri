package com.example.nutri

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.core.AlarmReceiver
import com.example.nutri.ui.navigation.BottomNavigationBar
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.navigation.NavigationGraph
import com.example.nutri.ui.theme.NutriTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @Inject
    lateinit var navControllerHolder: NavControllerHolder

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController: NavHostController = rememberNavController()

            navControllerHolder.navController = navController
            val notificationPermission = android.Manifest.permission.POST_NOTIFICATIONS
            if (ContextCompat.checkSelfPermission(this, notificationPermission) != PackageManager.PERMISSION_GRANTED){
                Log.w("NUTRI", "Notifications permission denied")
                requestPermissions(arrayOf(notificationPermission), 112)
            }

            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntentProcessor = Intent(this, AlarmReceiver::class.java)
            alarmIntentProcessor.action = getString(R.string.notification_action)
            val alarmPendingIntent: PendingIntent = PendingIntent
                .getBroadcast(this, 0, alarmIntentProcessor, PendingIntent.FLAG_IMMUTABLE)

            alarmManager.apply {
                Log.w("AlarmManager", "enabled alarm")
                setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + 5000,
                    AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                    alarmPendingIntent
                )
            }

            NutriTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) })
                {
                    NavigationGraph(
                      navController = navController,
                      paddingValues = it
                    )
                }
            }
        }
    }
    private fun setDefaultAlarms(){
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val breakfastAlarmIntent = Intent(this, AlarmReceiver::class.java)
        breakfastAlarmIntent.apply {
            action = getString(R.string.notification_action)
            putExtra("notification_description", resources.getString(R.string.breakfast_notification_description))
            putExtra("notification_title", resources.getString(R.string.breakfast_notification_title))
        }
        val breakfastPendingIntent: PendingIntent
        = PendingIntent.getBroadcast(
            this,
            0,
            breakfastAlarmIntent,
            PendingIntent.FLAG_IMMUTABLE)

        val lunchAlarmIntent = Intent(this, AlarmReceiver::class.java)
        breakfastAlarmIntent.apply {
            action = getString(R.string.notification_action)
            putExtra("notification_description", resources.getString(R.string.lunch_notification_description))
            putExtra("notification_title", resources.getString(R.string.lunch_notification_title))
        }
        val lunchPendingIntent: PendingIntent
                = PendingIntent.getBroadcast(
            this,
            0,
            lunchAlarmIntent,
            PendingIntent.FLAG_IMMUTABLE)

        val dinnerAlarmIntent = Intent(this, AlarmReceiver::class.java)
        breakfastAlarmIntent.apply {
            action = getString(R.string.notification_action)
            putExtra("notification_description", resources.getString(R.string.dinner_notification_description))
            putExtra("notification_title", resources.getString(R.string.dinner_notification_title))
        }
        val dinnerPendingIntent: PendingIntent
                = PendingIntent.getBroadcast(
            this,
            0,
            dinnerAlarmIntent,
            PendingIntent.FLAG_IMMUTABLE)

        val lunchTime: Long = 14*3600*1000; val breakfastTime : Long = 9*3600*1000; val dinnerTime : Long = 18*3600*1000

        alarmManager.apply {
            Log.w("AlarmManager", "enabled alarm")
            setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                breakfastTime,
                AlarmManager.INTERVAL_DAY,
                breakfastPendingIntent
            )
            setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                lunchTime,
                AlarmManager.INTERVAL_DAY,
                lunchPendingIntent
            )
            setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                dinnerTime,
                AlarmManager.INTERVAL_DAY,
                dinnerPendingIntent
            )
        }
    }
}