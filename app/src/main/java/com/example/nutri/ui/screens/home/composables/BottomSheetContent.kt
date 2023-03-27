package com.example.nutri.ui.screens.home.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .padding(16.dp),
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

    val scope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.HalfExpanded,
            confirmStateChange = {
                it != ModalBottomSheetValue.Hidden
            })

    ModalBottomSheetLayout(sheetContent = {

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.background
        ) {
            HomeBottomSheetContent(vm.meals)
        }
    },
        sheetElevation = 0.dp,
        sheetState = bottomSheetState,
        scrimColor = Color.Transparent) {
        HomePageStatistics(scope)
    }
}