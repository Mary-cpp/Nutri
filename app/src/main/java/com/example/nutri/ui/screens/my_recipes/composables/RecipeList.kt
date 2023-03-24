package com.example.nutri.ui.screens.my_recipes.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.nutri.domain.recipes.model.Recipe

@Composable
fun RecipesList(
    listOfRecipes: List<Recipe>,
    navController: NavController
){
    LazyColumn{
        items(listOfRecipes){
            RecipeListItem(recipe = it, navController)
        }
    }
}