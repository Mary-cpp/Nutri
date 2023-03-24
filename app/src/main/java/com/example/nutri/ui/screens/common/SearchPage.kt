package com.example.nutri.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.screens.home.StatisticsViewModel
import com.example.nutri.ui.theme.NutriTheme


@Composable
fun SearchPage(
    vm: StatisticsViewModel,
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

            val searchParam = remember{ mutableStateOf("") }
            val recipesList: MutableState<List<Recipe>> = remember { mutableStateOf(vm.foundRecipes.value) }

            SearchField(searchParameter = searchParam)

            RecipesListForSearch(listOfRecipes = recipesList.value)

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

            val searchParam = remember{ mutableStateOf("") }

            SearchField(searchParameter = searchParam)

            RecipesListForSearch(listOfRecipes = listOf(Recipe.makeRecipe(),Recipe.makeRecipe()))

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