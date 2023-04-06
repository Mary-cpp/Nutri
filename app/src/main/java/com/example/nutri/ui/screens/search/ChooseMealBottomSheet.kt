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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchPage(
    vm : SearchViewModel = hiltViewModel()
){
    val scope = rememberCoroutineScope()

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
                mealName = mealName,
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
    bottomSheetState: ModalBottomSheetState
){
    val tag = "MealBottomSheetContent"
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
                                    vm.navigateToHome()
                                }
                                Log.d(tag, mealName.value)
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