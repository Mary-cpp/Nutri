package com.example.nutri.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.ui.screens.common.TopBar
import com.example.nutri.ui.screens.home.composables.HomePageBottomSheet
import com.example.nutri.ui.screens.home.composables.MealFAB
import com.example.nutri.ui.screens.home.composables.StatisticsCard
import com.example.nutri.ui.theme.NutriTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomePage(
    navController : NavController,
    vm : StatisticsViewModel
) {
    Scaffold(topBar = {TopBar(topBarText = "Home")},
        floatingActionButton = { MealFAB(navController = navController) })
    { paddingValues ->
        Surface(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.background
        ) {
            HomePageBottomSheet(vm)
        }
    }
}

@Composable
fun HomePageStatistics(
    vm: StatisticsViewModel){

    vm.user.value?.let {
        StatisticsCard(current = vm.myCalories, norm = it.plan!!.kcal, vm.statisticsCardColor)
    }
}

@Preview
@Composable
fun HomePagePreview(){
    NutriTheme {
        HomePage(rememberNavController(), hiltViewModel())
    }
}