package com.example.nutri.ui.screens.home.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutri.ui.screens.MealFAB
import com.example.nutri.ui.screens.TopBar
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeBottomSheetScaffold(navController: NavController){

    BottomSheetScaffold(
        sheetContent = {HomeBottomSheetContent()},
        scaffoldState = rememberBottomSheetScaffoldState(),
        topBar = { TopBar(topBarText = "Home", navController = navController)},
        floatingActionButton = {MealFAB(navController = navController)},
        sheetElevation = 4.dp,
        sheetPeekHeight = 350.dp
    ) {

    }
}

@Composable
fun HomeBottomSheetContent(){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = NutriShape.smallRoundCornerShape,
        color = MaterialTheme.colors.primary
    ) {

        LazyColumn(){
            item { MealBigCard("Breakfast") }
            item { MealBigCard("Lunch") }
            item { MealBigCard("Dinner") }
        }

    }
}

@Preview
@Composable
fun HomeSheetPreview(){
    NutriTheme {
        HomeBottomSheetContent()
    }
}