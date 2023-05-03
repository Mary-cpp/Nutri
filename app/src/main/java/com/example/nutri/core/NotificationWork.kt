package com.example.nutri.core

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationWork @Inject constructor(
    @ApplicationContext val context: Context,
    params: WorkerParameters,
) : Worker(context, params) {

    override fun doWork(): Result {
        NotificationsHandler(context).enableAlarms()
        return Result.success()
    }
}