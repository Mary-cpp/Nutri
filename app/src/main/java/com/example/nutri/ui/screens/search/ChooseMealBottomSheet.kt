package com.example.nutri.ui.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.screens.DropDownListButton
import com.example.nutri.ui.theme.NutriShape

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchPage(
    navController: NavController,
    vm : SearchViewModel
){
    val scope = rememberCoroutineScope()
    val recipeId = remember{ mutableStateOf("") }

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = {
                it != ModalBottomSheetValue.Expanded
            }
        )

    val mealName: MutableState<String> = remember{ mutableStateOf("Select meal")}
    ModalBottomSheetLayout(
        sheetContent = {
            MealBottomSheetContent(
                mealName = mealName
            )
        },
        sheetState = bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetShape = NutriShape.mealsListCornerShape,
        sheetElevation = 8.dp,
    ) {
        SearchPageContent(
            navController = navController,
            vm = vm,
            scope = scope,
            bottomSheetState = bottomSheetState
        )
    }


    if (bottomSheetState.currentValue != ModalBottomSheetValue.Hidden){
        DisposableEffect(Unit) {
            onDispose {

                if(mealName.value.isNotEmpty()){

                    navController.navigate(
                        Screen
                        .Home
                        .screenRoute
                        .replace("{recipe_id}", "${vm.selectedRecipeId.value}}")
                    )

                    Log.d("BOTTOM SHEET", mealName.value)
                }
            }
        }
    }
}

@Composable
fun MealBottomSheetContent(mealName: MutableState<String>){

    val mealNames = listOf("Breakfast", "Lunch", "Dinner")

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        DropDownListButton(mutableString = mealName,
            color = MaterialTheme.colors.primary,
            shape = NutriShape.smallRoundCornerShape,
            menuItems = mealNames,
            buttonSize = 150)
    }
}