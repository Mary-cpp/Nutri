package com.example.nutri.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.R
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.screens.home.StatisticsViewModel
import com.example.nutri.ui.screens.home.composables.HomeBottomSheetContent
import com.example.nutri.ui.theme.NutriTheme
import kotlinx.coroutines.CoroutineScope

val menuItems = listOf(Screen.MyRecipes, Screen.Home, Screen.BMI)

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
fun MealFAB(
    navController: NavController
){

    FloatingActionButton(
        onClick = {
                  navController.navigate(Screen.EditRecipe.screenRoute)
         },
        modifier = Modifier.size(56.dp),

        backgroundColor = Color.Black,
        elevation = FloatingActionButtonDefaults.elevation(4.dp)) {

        Icon(
            ImageVector.vectorResource(id = R.drawable.add48px),
            contentDescription = "AddFAB",
            modifier = Modifier.size(24.dp),
            tint = Color.White)
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
            HomeBottomSheetContent(vm.meals.value)
        }
    },
        sheetElevation = 0.dp,
        sheetState = bottomSheetState,
        scrimColor = Color.Transparent) {
        HomePageStatistics(scope)
    }
}

@Composable
fun HomePageStatistics(scope: CoroutineScope){

}

@Preview
@Composable
fun HomePagePreview(){
    NutriTheme {
        HomePage(rememberNavController(), hiltViewModel())
    }
}