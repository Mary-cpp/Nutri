package com.example.nutri.ui.screens.home.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun RecipeInMealCard(
    recipe: Recipe
){
    Card(
        modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        shape = NutriShape.mediumRoundedCornerShape,
        backgroundColor = MaterialTheme.colors.surface
    ){
        Row(
            modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            recipe.name?.let{
                Text(
                    modifier = Modifier.wrapContentHeight().widthIn(max = 200.dp),
                    text = recipe.name
                )
                Text(text = "${recipe.calories} kcal")
            }
        }
    }
}

@Composable
fun NoRecipes(){
    Card(
        modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        shape = NutriShape.mediumRoundedCornerShape,
        backgroundColor = MaterialTheme.colors.surface
    ){
        Row(
            modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = LocalContext.current.getString(R.string.no_meals))
        }

    }
}

@Composable
fun RecipeInMealCardTest(
    recipeName : String,
    recipeWeight: String
){
    Card(
        modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        shape = NutriShape.mediumRoundedCornerShape,
        backgroundColor = MaterialTheme.colors.surface
    ){
        Row(
            modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = recipeName)
            Text(text = recipeWeight)
        }
    }
}

@Preview
@Composable
fun RecipeInMealCardTestPreview(){
    NutriTheme {
        RecipeInMealCardTest(recipeName = "Meal1", recipeWeight = "100.0g")
    }
}

@Preview
@Composable
fun NoRecipesPreview(){
    NutriTheme {
        NoRecipes()
    }
}