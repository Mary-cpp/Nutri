package com.example.nutri.ui.screens.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.recipes.model.Recipe
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipesListForSearch(
    listOfRecipes: MutableState<List<Recipe>>,
    recipeId: MutableState<String>,
    scope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState
){
    LazyColumn(modifier = Modifier.padding(24.dp)){
        items(listOfRecipes.value){
            RecipeSearchListItem(
                recipe = it,
                recipeId = recipeId,
                scope = scope,
                bottomSheetState = bottomSheetState
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipesListForSearchTest(
    listOfRecipes: List<Recipe>){
    LazyColumn(modifier = Modifier.padding(24.dp)){
        items(listOfRecipes){
            RecipeSearchListItem(
                recipe = it,
                recipeId = remember{mutableStateOf("")},
                scope = rememberCoroutineScope(),
                bottomSheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden
                )
            )
        }
    }
}