package com.example.nutri.ui.screens.search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchPage(
    navController: NavController,
    vm : SearchViewModel
){
    val scope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = {
                it != ModalBottomSheetValue.Expanded
            }
        )

    val mealName: MutableState<String> = remember{ mutableStateOf("Select meal")}
    if (bottomSheetState.currentValue != ModalBottomSheetValue.Hidden){
        DisposableEffect(Unit) {
            onDispose {

                if(mealName.value!="Select meal"){

                    Log.d("BOTTOM SHEET", mealName.value)
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            MealBottomSheetContent(
                mealName = mealName,
                navController = navController,
                vm = vm,
                bottomSheetState = bottomSheetState
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
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MealBottomSheetContent(
    vm: SearchViewModel,
    mealName: MutableState<String>,
    navController: NavController,
    bottomSheetState: ModalBottomSheetState
){

    val mealNames = listOf("Breakfast", "Lunch", "Dinner")
    val scope = rememberCoroutineScope()

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Text(
            text = mealName.value,
            color = MaterialTheme.colors.onBackground
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ){
            items(mealNames){
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .size(48.dp)
                        .clickable {

                            mealName.value = it

                            if (mealName.value != "Select meal") {

                                scope.launch {

                                    vm.addRecipeToMeal(
                                        id = vm.selectedRecipeId.value,
                                        mealName = it
                                    )

                                    bottomSheetState.hide()

                                    navController.navigate(
                                        Screen.Home.screenRoute
                                    )
                                }

                                Log.d("BOTTOM SHEET", mealName.value)
                            }
                        },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Column(verticalArrangement = Arrangement.Center) {

                        Text(text = it,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MealBottomSheetContentTest(
    mealName: MutableState<String>
){

    val mealNames = listOf("Breakfast", "Lunch", "Dinner")

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Text(text = mealName.value,
            color = MaterialTheme.colors.onBackground)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ){
            items(mealNames){
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .size(48.dp),
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Column(verticalArrangement = Arrangement.Center) {

                        Text(text = it,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun BottomSheetPreview(){
    NutriTheme {
        MealBottomSheetContentTest(
            mealName = remember { mutableStateOf("Select Meal") })
    }
}