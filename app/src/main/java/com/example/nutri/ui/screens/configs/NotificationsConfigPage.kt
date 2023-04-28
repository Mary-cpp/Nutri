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
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.screens.common.TopBarWithIcon
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun NotificationsConfigPage(
    modifier: Modifier = Modifier,
    vm : NotificationsConfigViewModel,
){
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = { TopBarWithIcon("Notifications", action = vm::navigateBack) },
    ) { paddingValues ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn {
                item {
                    MealsNotifications(
                        switchState = vm.areMealNotificationsEnabled,
                        onSwitchStateChanged = vm::onMealNotificationSwitchStateChanged,
                        onTimeSelected = vm::onMealNotificationTimeChanged
                    )
                }
                item{
                    WaterNotifications(
                        switchState = vm.areWaterNotificationsEnabled,
                        text = "Water notifications",
                        onSwitchStateChanged = vm::onWaterNotificationsSwitchStateChanged)
                }
            }
        }
    }
}

@Composable
fun WaterNotifications(
    switchState: MutableState<Boolean>,
    onSwitchStateChanged: (Boolean) -> Unit,
    text: String,
){
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
            Switch(checked = switchState.value, onCheckedChange = {
                switchState.value = !switchState.value
                onSwitchStateChanged.invoke(switchState.value)
            })
        }
    }
}

@Composable
fun MealsNotifications(
    switchState: MutableState<Boolean>,
    onSwitchStateChanged: (Boolean) -> Unit,
    onTimeSelected: (NotificationType, Int, Int) -> Unit,
){
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
            Switch(checked = switchState.value, onCheckedChange = {
                switchState.value = !switchState.value
                onSwitchStateChanged.invoke(switchState.value)
            })
        }
        MealTimeConfigurations(onTimeSelected)
    }
}

@Composable
fun MealTimeConfigurations(
    onTimeSelected: (NotificationType, Int, Int) -> Unit,
){
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
                if (notificationType == NotificationType.WATER) return@forEach
                MealTimeConfigurator(mealName = notificationType.text, onTimeSelected = onTimeSelected)
            }
        }
    }
}

@Composable
fun MealTimeConfigurator(
    mealName: String,
    onTimeSelected: (NotificationType, Int, Int) -> Unit,
){
    val mealNotificationType = NotificationType.valueOf(mealName.uppercase())

    val minutes by remember { derivedStateOf {
        if (mealNotificationType.triggerTimeMinutes in 0..9) "0${mealNotificationType.triggerTimeMinutes}"
        else mealNotificationType.triggerTimeMinutes
    }}
    var time by remember { mutableStateOf("${mealNotificationType.triggerTimeHours}:$minutes") }

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        {_, mHour : Int, mMinute: Int ->
            onTimeSelected.invoke(mealNotificationType, mHour, mMinute)
            time = "$mHour:$mMinute" },
        mealNotificationType.triggerTimeHours,
        mealNotificationType.triggerTimeMinutes,
        false
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