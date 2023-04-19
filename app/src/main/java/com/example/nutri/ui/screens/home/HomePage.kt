package com.example.nutri.ui.screens.home

import androidx.compose.foundation.BorderStroke
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
import com.example.nutri.ui.screens.common.TopBar
import com.example.nutri.ui.screens.home.composables.FAB
import com.example.nutri.ui.screens.home.composables.HomePageBottomSheet
import com.example.nutri.ui.screens.home.composables.StatisticsCard

@Composable
fun HomePage(
    vm : StatisticsViewModel
) {
    Scaffold(topBar = {TopBar(topBarText = "Home")},
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
    vm: StatisticsViewModel){

    StatisticsCard(current = vm.myCalories, norm = vm.user.value?.plan?.kcal, vm.statisticsCardColor)
}