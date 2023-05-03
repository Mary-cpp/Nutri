package com.example.nutri.ui.screens.common

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun HomeScreenTopBar(
    topBarText: String,
    actionCalendar: (Int, Int, Int) -> Unit,
    actionNotifications: () -> Unit,
) {

    val year = Calendar.getInstance().get(Calendar.YEAR)
    val month = Calendar.getInstance().get(Calendar.MONTH)
    val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { dp: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
            actionCalendar.invoke(mDay, mMonth+1, mYear)
        }, year, month, day
    )

    TopAppBar(
        title = { Text(text = topBarText, color = Color.Black) },
        actions = {
            IconButton(
                onClick = { datePickerDialog.show()},
                modifier = Modifier.fillMaxHeight()) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.example.nutri.R.drawable.calendar48px),
                    contentDescription = "DatePicker",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            }
            IconButton(onClick = { actionNotifications.invoke()}, modifier = Modifier.fillMaxHeight()) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.example.nutri.R.drawable.edit_notifications_48px),
                    contentDescription = "NavigateToNotificationsConfigurations",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}