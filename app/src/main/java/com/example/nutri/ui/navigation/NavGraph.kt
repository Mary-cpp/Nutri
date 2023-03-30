package com.example.nutri.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nutri.ui.screens.BmiPage
import com.example.nutri.ui.screens.HomePage
import com.example.nutri.ui.screens.RecipeEditPage
import com.example.nutri.ui.screens.RecipePage
import com.example.nutri.ui.screens.bmi.BmiViewModel
import com.example.nutri.ui.screens.create_recipe.CreateRecipeViewModel
import com.example.nutri.ui.screens.my_recipes.MyRecipesPage
import com.example.nutri.ui.screens.my_recipes.MyRecipesViewModel
import com.example.nutri.ui.screens.recipe.RecipeViewModel

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Home.screenRoute){
        composable(Screen.Home.screenRoute) { HomePage(navController) }
        composable(Screen.MyRecipes.screenRoute) {

            val vm = hiltViewModel<MyRecipesViewModel>()

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
            route = Screen.EditRecipe.screenRoute,
            /*arguments = listOf(navArgument("recipe_id"){
                type = NavType.IntType
                nullable = true
            }
            )*/
        ) {

            val vm = hiltViewModel<CreateRecipeViewModel>()

            /*it.arguments?.let{
                vm.apply {

                    val id = it.getInt("recipe_id")
                    getRecipeInfoIfEditing(id)
                }
            }*/

            RecipeEditPage(
                vm = vm,
                navController = navController)
        }
        composable(Screen.Recipe.screenRoute,
            arguments = listOf(navArgument("recipe_id"){type = NavType.IntType})
        ) { backStackEntry ->

            val vm = hiltViewModel<RecipeViewModel>().apply {
                recipeId.value = backStackEntry.arguments?.getInt("recipe_id") as Int
                onRecipeScreenLoading()
            }
            RecipePage(
                vm = vm,
                navController = navController
            )
        }
    }
}