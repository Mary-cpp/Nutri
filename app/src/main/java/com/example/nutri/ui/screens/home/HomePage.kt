package com.example.nutri.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.R
import com.example.nutri.ui.navigation.BottomNavigationBar
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme
import kotlinx.coroutines.CoroutineScope

val menuItems = listOf(Screen.MyRecipes, Screen.Home, Screen.BMI)

@Composable
fun HomePage(navController : NavController) {
    Scaffold(topBar = {TopBar(topBarText = "Home")},
        bottomBar = { BottomNavigationBar(navController = navController) },
        floatingActionButton = { MealFAB(navController = navController) })
    { paddingValues ->
        Surface(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.background
        ) {
            HomePageBottomSheet()
        }
    }


}

@Composable
fun MealFAB(
    navController: NavController
){

    FloatingActionButton(onClick = { //navController.navigate(Screen.EditRecipe.screenRoute)
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
fun HomePageBottomSheet(){

    val scope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.HalfExpanded,
            confirmStateChange = {
                it != ModalBottomSheetValue.Hidden
            })

    ModalBottomSheetLayout(sheetContent = {

        Surface(color = MaterialTheme.colors.background) {
            HomeBottomSheetContent()
        }
    },
    sheetState = bottomSheetState) {
        HomePageStatistics(scope)
    }

}

@Composable
fun HomeBottomSheetContent(){

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp),
    shape = NutriShape.mediumRoundedCornerShape,
    color = MaterialTheme.colors.primary) {

        LazyColumn(){
            item { MealBigCard("Breakfast") }
            item { MealBigCard("Lunch") }
        }
    }
}

@Composable
fun MealBigCard(mealCommonTitle: String){

    Column(modifier = Modifier.padding(16.dp)) {

        Text(text = mealCommonTitle,
        fontSize = MaterialTheme.typography.h6.fontSize,
        modifier = Modifier.padding(bottom = 16.dp, start = 10.dp))

        Card(modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.background,
            shape = NutriShape.mediumRoundedCornerShape,
        ){

            Column (verticalArrangement = Arrangement.SpaceBetween)
            {
                MealSmallCard("Meal1", "100g")
                MealSmallCard("Meal2", "200g")
            }
        }
    }
}

@Composable
fun MealSmallCard(
    mealTitle: String,
    amount: String
){

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        shape = NutriShape.mediumRoundedCornerShape,
        backgroundColor = MaterialTheme.colors.surface) {

        Row (modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = mealTitle)
            Text(text = amount)
        }

    }
}

@Composable
fun HomePageStatistics(scope: CoroutineScope){

}

@Preview
@Composable
fun HomePagePreview(){
    NutriTheme {
        HomePage(rememberNavController())
    }
}