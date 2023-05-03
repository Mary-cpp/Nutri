package com.example.nutri.ui.screens.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.statistics.Meal
import com.example.nutri.ui.screens.home.HomePageStatistics
import com.example.nutri.ui.screens.home.StatisticsViewModel
import com.example.nutri.ui.theme.NutriShape

@Composable
fun HomeBottomSheetContent(meals: List<Meal>){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        shape = NutriShape.mealsListCornerShape,
        color = MaterialTheme.colors.primary
    ) {
        LazyColumn{
            items(meals){
                MealBigCard(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomePageBottomSheet(vm: StatisticsViewModel){
    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
        ),
        sheetPeekHeight = countSheetHeight(listOfMeals = vm.meals.value),
        sheetContent = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.background
            ){
                Column{
                    HomeBottomSheetContent(vm.meals.value)
                }
            }
    },
        sheetElevation = 0.dp,
        contentColor = Color.Transparent) {
        HomePageStatistics(vm)
    }
}

@Composable
private fun countSheetHeight(listOfMeals: List<Meal>) : Dp {
    val screenHalfSize : Dp = (LocalConfiguration.current.screenHeightDp/2).dp
    val sheetPeekHeight : Dp = BottomSheetScaffoldDefaults.SheetPeekHeight
    return if (listOfMeals.size == 1 ) sheetPeekHeight
    else{
        var dishesAmount = 0
        listOfMeals.forEach {
            dishesAmount += it.recipes.size
        }
        if (dishesAmount > 3) screenHalfSize
        else sheetPeekHeight
    }
}