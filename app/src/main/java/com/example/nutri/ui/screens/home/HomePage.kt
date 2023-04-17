package com.example.nutri.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nutri.ui.screens.common.TopBar
import com.example.nutri.ui.screens.home.composables.HomePageBottomSheet
import com.example.nutri.ui.screens.home.composables.MealFAB
import com.example.nutri.ui.screens.home.composables.StatisticsCard

@Composable
fun HomePage(
    vm : StatisticsViewModel
) {
    Scaffold(topBar = {TopBar(topBarText = "Home")},
        floatingActionButton = { MealFAB(vm::navigateToSearch) })
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