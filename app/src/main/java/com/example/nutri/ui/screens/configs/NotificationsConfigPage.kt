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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nutri.core.NotificationType
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.screens.common.TopBarWithIcon
import com.example.nutri.ui.theme.NutriTheme
import java.util.Calendar

@Composable
fun NotificationsConfigPage(
    modifier: Modifier = Modifier,
    vm : NotificationsConfigViewModel = hiltViewModel()
){
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = { TopBarWithIcon("Configurations", action = vm::navigateBack) },
    ) { paddingValues ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn {
                item {
                    MealsNotifications(onSwitchStateChanged = vm::onMealNotificationSwitchStateChanged)
                }
                item{
                    WaterNotifications(text = "Water notifications", onSwitchStateChanged = vm::onWaterNotificationsSwitchStateChanged)
                }
            }
        }
    }
}

@Composable
fun WaterNotifications(
    onSwitchStateChanged: (Boolean) -> Unit,
    text: String,
){

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
            Switch(checked = switchState, onCheckedChange = {
                switchState = !switchState
                onSwitchStateChanged.invoke(switchState)
            })
        }
    }
}

@Composable
fun MealsNotifications(
    onSwitchStateChanged: (Boolean) -> Unit,
){
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
            Switch(checked = switchState, onCheckedChange = {
                switchState = !switchState
                onSwitchStateChanged.invoke(switchState)
            })
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
                if (notificationType == NotificationType.WATER) return@Surface
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
            time = "$mHour:$mMinute" }, hourOfDay, minute, false
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
        NotificationsConfigPage(vm = NotificationsConfigViewModel(LocalContext.current, NavControllerHolder()))
    }
}