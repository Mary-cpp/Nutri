package com.example.nutri

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.core.NotificationsHandler
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

            NotificationsHandler(
                context = this
            ).setDefaultAlarms()

            //setDefaultAlarms()

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
/*
    private fun setDefaultAlarms(){
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(this, AlarmReceiver::class.java)

        NotificationType.values().forEach { notification ->
            alarmIntent.apply {
                action = getString(R.string.notification_action)
                putExtra("notification_title", resources.getString(notification.title))
                putExtra("notification_description", resources.getString(notification.description))
            }

            val pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE)
            notification.intent = pendingIntent

            alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                notification.triggerTimeInMillis,
                1800*1000,
                notification.intent
            ).apply {
                Log.w("ALARM MANAGER", "Enabled ${notification.text} alarm")
            }
        }
    }*/
}