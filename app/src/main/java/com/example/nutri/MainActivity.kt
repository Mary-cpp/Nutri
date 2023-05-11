package com.example.nutri

import android.content.Context
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
import com.example.nutri.ui.screens.configs.NUTRI_PREFERENCES
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

        val notificationPermission = android.Manifest.permission.POST_NOTIFICATIONS
        if (ContextCompat.checkSelfPermission(this, notificationPermission) != PackageManager.PERMISSION_GRANTED){
            Log.w(this::class.java.simpleName, "Notifications permission denied")
            requestPermissions(arrayOf(notificationPermission), 112)
        }

        val sp = getSharedPreferences(NUTRI_PREFERENCES, Context.MODE_PRIVATE)

        if (sp.getBoolean("isFirstRun", true)){
            NotificationsHandler(context = this).enableAlarms()
            sp.edit().putBoolean("isFirstRun", false).apply()
        }
        /*val notificationsWorkRequest = PeriodicWorkRequestBuilder<NotificationWork>(24,TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueue(notificationsWorkRequest)*/

        setContent {
            val navController: NavHostController = rememberNavController()

            navControllerHolder.navController = navController

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
}