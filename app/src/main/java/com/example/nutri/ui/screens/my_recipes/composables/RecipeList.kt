package com.example.nutri.ui.screens.my_recipes.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.nutri.domain.recipes.model.Recipe

@Composable
fun RecipesList(
    listOfRecipes: List<Recipe>,
    navigateToRecipe: (String, Recipe) -> Unit
){
    LazyColumn{
        items(listOfRecipes){
            RecipeListItem(recipe = it, navigateToRecipe)
        }
    }
}