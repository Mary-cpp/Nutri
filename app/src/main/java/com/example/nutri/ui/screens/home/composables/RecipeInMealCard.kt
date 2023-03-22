package com.example.nutri.ui.screens.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
fun RecipeInMealCard(
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