package com.example.nutri.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.theme.NutriShape

@Composable
fun RecipeSearchListItem(
    recipe: Recipe,
    navController: NavController
){
    Card(modifier = Modifier
        .fillMaxWidth(1f)
        .padding(2.dp)
        .clickable {
            navController.navigate(Screen
                .Home
                .screenRoute
                .replace("{recipe_id}", "${recipe.id}")
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        shape = NutriShape.smallRoundCornerShape
    ) {
        Column() {
            Text(text = recipe.name!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, top = 16.dp, bottom = 10.dp, end = 16.dp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize)

            Text(text = "Calories: ${recipe.calories}",
                modifier = Modifier
                    .padding(start = 32.dp, bottom = 24.dp),
                fontSize = MaterialTheme.typography.subtitle2.fontSize
            )
        }
    }
}