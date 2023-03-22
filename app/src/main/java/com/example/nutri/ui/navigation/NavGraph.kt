package com.example.nutri.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.recipe.local.repository.DataBaseGatewayImpl
import com.example.nutri.domain.recipes.interactor.LocalRecipeUseCase
import com.example.nutri.ui.screens.*
import com.example.nutri.ui.screens.my_recipes.MyRecipesViewModel
import com.example.nutri.ui.screens.recipe.RecipeViewModel
import com.example.nutri.ui.screens.bmi.BmiViewModel

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Home.screenRoute){
        composable(Screen.Home.screenRoute) { HomePage(navController) }
        composable(Screen.MyRecipes.screenRoute) {
            MyRecipesPage(
                vm = MyRecipesViewModel(
                    useCase = LocalRecipeUseCase(
                        db = DataBaseGatewayImpl(database = RecipeDatabase.getDatabase(context = LocalContext.current))
                    )
                ),
                navController
            )
        }
        composable(Screen.BMI.screenRoute){

            val vm = hiltViewModel<BmiViewModel>()

            BmiPage(
                vm = vm,
                navController = navController)
        }
        composable(Screen.EditRecipe.screenRoute) { RecipeEditPage(navController = navController) }
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