package com.example.nutri.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.screens.common.RecipesListForSearch
import com.example.nutri.ui.screens.common.RecipesListForSearchTest
import com.example.nutri.ui.screens.common.SearchField
import com.example.nutri.ui.theme.NutriTheme
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchPageContent(
    vm: SearchViewModel,
    navController: NavController,
    scope : CoroutineScope,
    bottomSheetState: ModalBottomSheetState
){

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){
        Column(
            modifier = Modifier.padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchField(vm = vm)

            RecipesListForSearch(
                listOfRecipes = vm.foundRecipes,
                recipeId = vm.selectedRecipeId,
                scope = scope,
                bottomSheetState = bottomSheetState
            )

            Text(
                modifier = Modifier
                    .clickable { navController.navigate(Screen.EditRecipe.screenRoute) },
                text = "Didn't find desired Recipe? Create!"
            )
        }
    }
}

@Composable
fun SearchPageTest(
    navController: NavController
){
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){
        Column(
            modifier = Modifier.padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchField()

            RecipesListForSearchTest(listOfRecipes = listOf(Recipe.makeRecipe(),Recipe.makeRecipe()))

            Text(
                modifier = Modifier
                    .clickable { navController.navigate(Screen.EditRecipe.screenRoute) },
                text = "Didn't find desired Recipe? Create!"
            )
        }
    }
}

@Preview
@Composable
fun SearchPagePreview(){
    NutriTheme {
        SearchPageTest(navController = rememberNavController())
    }
}