package com.example.nutri.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.repository.DataBaseGatewayImpl
import com.example.nutri.domain.recipes.interactor.LocalRecipeUseCase
import com.example.nutri.ui.screens.*
import com.example.nutri.ui.viewmodel.MyRecipesViewModel

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
        composable(Screen.BMI.screenRoute){ BmiPage(navController = navController) }
        composable(Screen.EditRecipe.screenRoute) { RecipeEditPage(navController = navController) }
        composable(Screen.Recipe.screenRoute,
            arguments = listOf(navArgument("recipe_id"){type = NavType.IntType})
        ) { backStackEntry ->
            RecipePage(navController = navController,
                recipeId = backStackEntry.arguments?.getInt("recipe_id") as Int
            ) }
    }
}