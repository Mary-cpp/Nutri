package com.example.nutri.ui.screens.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.screens.RecipeSearchListItem

@Composable
fun RecipesListForSearch(
    listOfRecipes: List<Recipe>
){
    LazyColumn(modifier = Modifier.padding(24.dp)){
        items(listOfRecipes){
            RecipeSearchListItem(recipe = it)
        }
    }
}