package com.example.nutri.ui.screens.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.domain.recipes.model.Recipe

@Composable
fun RecipesListForSearch(
    listOfRecipes: MutableState<List<Recipe>>,
    navController: NavController
){
    LazyColumn(modifier = Modifier.padding(24.dp)){
        items(listOfRecipes.value){
            RecipeSearchListItem(
                recipe = it,
                navController = navController
            )
        }
    }
}

@Composable
fun RecipesListForSearchTest(
    listOfRecipes: List<Recipe>){
    LazyColumn(modifier = Modifier.padding(24.dp)){
        items(listOfRecipes){
            RecipeSearchListItem(recipe = it, rememberNavController())
        }
    }
}