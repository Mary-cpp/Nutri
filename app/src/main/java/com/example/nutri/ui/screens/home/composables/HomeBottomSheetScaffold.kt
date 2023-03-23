package com.example.nutri.ui.screens.home.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.statistics.Meal
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun HomeBottomSheetContent(meals: List<Meal>){

    val listOfMeals = remember {   mutableStateListOf(meals) }

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

@Composable
fun HomeBottomSheetContentTest(){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start =  16.dp, end = 16.dp),
        shape = NutriShape.mealsListCornerShape,
        color = MaterialTheme.colors.primary
    ) {

        LazyColumn(){
            item { MealBigCardTest() }
            item { MealBigCardTest() }
            item { MealBigCardTest() }
        }

    }
}

@Preview
@Composable
fun HomeSheetPreview(){
    NutriTheme {
        HomeBottomSheetContentTest()
    }
}