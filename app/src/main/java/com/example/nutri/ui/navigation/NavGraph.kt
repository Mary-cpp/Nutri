package com.example.nutri.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.repository.DataBaseGatewayImpl
import com.example.nutri.domain.interactor.LocalRecipeUseCase
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
        composable(Screen.Recipe.screenRoute) { RecipePage(navController = navController) }
    }
}