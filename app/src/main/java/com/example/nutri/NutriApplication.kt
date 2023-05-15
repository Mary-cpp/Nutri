package com.example.nutri

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.nutri.core.NotificationWork
import com.example.nutri.ui.screens.configs.NUTRI_PREFERENCES
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class NutriApplication : Application(), ActivityLifecycleCallbacks{

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate() {
        val sp = getSharedPreferences(NUTRI_PREFERENCES, Context.MODE_PRIVATE)
        val notificationPermission = android.Manifest.permission.POST_NOTIFICATIONS
        if (!sp.getBoolean("isFirstRun", true)) return

        if (ContextCompat.checkSelfPermission(this, notificationPermission) != PackageManager.PERMISSION_GRANTED) {
            val notificationsWorkRequest =
                PeriodicWorkRequestBuilder<NotificationWork>(24, TimeUnit.HOURS).build()
            WorkManager.getInstance(this).enqueue(notificationsWorkRequest)
            sp.edit().putBoolean("isFirstRun", false).apply()
        }
        super.onCreate()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        val notificationPermission = android.Manifest.permission.POST_NOTIFICATIONS
        if (ContextCompat.checkSelfPermission(this, notificationPermission) != PackageManager.PERMISSION_GRANTED){
            Log.w(this::class.java.simpleName, "Notifications permission denied")
            p0.requestPermissions(arrayOf(notificationPermission), 112)
        }
    }

    override fun onActivityStarted(p0: Activity) {
        super.onActivityPostStarted(p0)
    }

    override fun onActivityResumed(p0: Activity) {
        super.onActivityPostResumed(p0)
    }

    override fun onActivityPaused(p0: Activity) {
        super.onActivityPostPaused(p0)
    }

    override fun onActivityStopped(p0: Activity) {
        super.onActivityPostStopped(p0)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        super.onActivityPostSaveInstanceState(p0, p1)
    }

    override fun onActivityDestroyed(p0: Activity) {
        super.onActivityPostDestroyed(p0)
    }
}