package com.example.nutri.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.ui.screens.common.HomeScreenTopBar
import com.example.nutri.ui.screens.home.composables.FAB
import com.example.nutri.ui.screens.home.composables.HomePageBottomSheet
import com.example.nutri.ui.screens.home.composables.StatisticsCard
import com.example.nutri.ui.screens.home.composables.WaterInfoCard

@Composable
fun HomePage(
    vm : StatisticsViewModel
) {
    Scaffold(topBar = { HomeScreenTopBar(
        topBarText = "Home",
        actionCalendar = vm::onDateSelected,
        actionNotifications = vm::navigateToNotificationsConfigs
    ) },
        floatingActionButton = { FAB(
            onClick = vm::navigateToSearch,
            color = Color.Black,
            border = BorderStroke(2.dp, Color.White),
            modifier = Modifier.size(54.dp),
            iconRes = R.drawable.add48px
        ) })
    { paddingValues ->
        Surface(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.background
        ){
            HomePageBottomSheet(vm)
        }
    }
}

@Composable
fun HomePageStatistics(
    vm: StatisticsViewModel
){
    Column{
        StatisticsCard(
            current = vm.myCalories,
            norm = vm.user.value?.plan?.kcal,
            vm.statisticsCardColor
        )
        WaterInfoCard(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            onRemove = vm::onWaterRemoveButtonClicked,
            onAdd = vm::onWaterAddButtonClicked,
            water = vm.water,
        )
    }
}