package com.example.nutri.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nutri.ui.screens.create_recipe.CreateRecipePage
import com.example.nutri.ui.screens.bmi.BmiPage
import com.example.nutri.ui.screens.bmi.BmiViewModel
import com.example.nutri.ui.screens.create_recipe.CreateRecipeViewModel
import com.example.nutri.ui.screens.home.HomePage
import com.example.nutri.ui.screens.home.StatisticsViewModel
import com.example.nutri.ui.screens.my_recipes.MyRecipesPage
import com.example.nutri.ui.screens.my_recipes.MyRecipesViewModel
import com.example.nutri.ui.screens.recipe.RecipePage
import com.example.nutri.ui.screens.recipe.RecipeViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.screenRoute,
        modifier = Modifier.fillMaxSize().padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = paddingValues.calculateBottomPadding())
    ){
        composable(Screen.Home.screenRoute) {
            val vm = hiltViewModel<StatisticsViewModel>()
            HomePage(navController, vm) }
        composable(Screen.MyRecipes.screenRoute) {

            val vm = hiltViewModel<MyRecipesViewModel>().apply {
                getSavedRecipes()
            }

            MyRecipesPage(
                vm = vm,
                navController
            )
        }
        composable(Screen.BMI.screenRoute){

            val vm = hiltViewModel<BmiViewModel>()

            BmiPage(
                vm = vm,
                navController = navController)
        }
        composable(
            route = Screen.CreateRecipe.screenRoute,
        ) {

            val vm = hiltViewModel<CreateRecipeViewModel>()

            CreateRecipePage(
                vm = vm,
                navController = navController)
        }
        composable(Screen.Recipe.screenRoute,
            arguments = listOf(navArgument("recipe_id"){type = NavType.StringType})
        ) { backStackEntry ->

            val vm = hiltViewModel<RecipeViewModel>().apply {
                val id = backStackEntry.arguments?.getString("recipe_id") as String
                onRecipeScreenLoading(id)
            }
            RecipePage(
                vm = vm,
                navController = navController
            )
        }
    }
}