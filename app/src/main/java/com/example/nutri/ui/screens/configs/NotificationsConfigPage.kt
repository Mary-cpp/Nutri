package com.example.nutri.ui.screens.configs

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.core.NotificationType
import com.example.nutri.ui.screens.common.TopBar
import com.example.nutri.ui.theme.NutriTheme
import java.util.Calendar

@Composable
fun NotificationsConfigPage(
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = { TopBar("Configurations") },
    ) { paddingValues ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn {
                item {
                    MealsNotifications()
                }
                item{
                    ConfigItemWithSwitch(text = "Water notifications")
                }
            }
        }
    }
}

@Composable
fun ConfigItemWithSwitch(text: String){

    var switchState by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Divider(modifier = Modifier.padding(top = 2.dp, start = 4.dp, end = 4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = text,
                color = MaterialTheme.colors.onSurface,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
            Switch(checked = switchState, onCheckedChange = { switchState = !switchState})
        }
    }
}

@Composable
fun MealsNotifications(){
    var switchState by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Divider(modifier = Modifier.padding(top = 2.dp, start = 4.dp, end = 4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "Meal notifications",
                color = MaterialTheme.colors.onSurface,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
            Switch(checked = switchState, onCheckedChange = { switchState = !switchState})
        }
        MealTimeConfigurations()
    }
}

@Composable
fun MealTimeConfigurations(){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            verticalArrangement = Arrangement.Center
        ){
            NotificationType.values().forEach { notificationType ->
                MealTimeConfigurator(mealName = notificationType.text)
            }
        }
    }
}

@Composable
fun MealTimeConfigurator(mealName: String){

    var time by remember { mutableStateOf("00:00") }

    val hourOfDay = Calendar.getInstance()[Calendar.HOUR_OF_DAY]
    val minute = Calendar.getInstance()[Calendar.MINUTE]

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        {_, mHour : Int, mMinute: Int ->
            time = "$mHour:$mMinute"
        }, hourOfDay, minute, false
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { timePickerDialog.show() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = mealName,
            color = MaterialTheme.colors.onSurface,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
        Text(
            modifier = Modifier.padding(12.dp),
            text = time,
            color = Color.Gray,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}

@Composable
@Preview
fun NotificationConfigPagePreview(){
    NutriTheme {
        NotificationsConfigPage()
    }
}