package com.example.nutri.ui.screens.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.theme.NutriShape

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
                RecipeInMealCard("Meal1", "100g")
                RecipeInMealCard("Meal2", "200g")
            }
        }
    }
}