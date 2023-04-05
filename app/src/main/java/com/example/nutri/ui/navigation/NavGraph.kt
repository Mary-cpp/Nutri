package com.example.nutri.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nutri.ui.screens.bmi.BmiPage
import com.example.nutri.ui.screens.create_recipe.CreateRecipePage
import com.example.nutri.ui.screens.edit_recipe.EditRecipePage
import com.example.nutri.ui.screens.edit_recipe.EditRecipeViewModel
import com.example.nutri.ui.screens.home.HomePage
import com.example.nutri.ui.screens.home.StatisticsViewModel
import com.example.nutri.ui.screens.my_recipes.MyRecipesPage
import com.example.nutri.ui.screens.my_recipes.RecipesViewModel
import com.example.nutri.ui.screens.recipe.RecipePage
import com.example.nutri.ui.screens.recipe.RecipeViewModel
import com.example.nutri.ui.screens.search.SearchPage

private const val TAG = "NAVIGATION"
@Composable
fun NavigationGraph(
    paddingValues: PaddingValues,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.screenRoute,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 0.dp,
                end = 0.dp,
                top = 0.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
    ){
        composable(Screen.Home.screenRoute) { navEntry ->
            val vm = hiltViewModel<StatisticsViewModel>()
            navEntry.lifecycle.addObserver(vm)

            HomePage(vm = vm)
        }
        composable(Screen.MyRecipes.screenRoute) { navEntry->

            Log.i(TAG, "To ${navEntry.destination.route}")

            val vm = hiltViewModel<RecipesViewModel>()
            navEntry.lifecycle.addObserver(vm)

            MyRecipesPage(vm = vm)
        }
        composable(Screen.SearchPage.screenRoute){
            SearchPage()
        }
        composable(Screen.BMI.screenRoute){
            BmiPage()
        }
        composable(
            route = Screen.CreateRecipe.screenRoute,
        ) {
            CreateRecipePage()
        }
        composable(
            route = Screen.EditRecipe.screenRoute,
            arguments = listOf(navArgument("recipe_id"){type = NavType.StringType})
        ) { backStackEntry ->

            val vm = hiltViewModel<EditRecipeViewModel>().apply {

                val id = backStackEntry.arguments?.getString("recipe_id") as String
                Log.i(TAG, "onEditRecipePageLoaded")
                onEditRecipePageLoaded(id)
            }
            EditRecipePage(vm = vm)
        }
        composable(
            route = "${Screen.Recipe.screenRoute}/{recipe_id}",
            arguments = listOf(navArgument("recipe_id"){type = NavType.StringType})
        ) { backStackEntry ->

            var id: String
            backStackEntry.arguments?.let{ bundle->
                id = bundle.getString("recipe_id") as String
                Log.d(TAG, id)

                val vm = hiltViewModel<RecipeViewModel>()

                LaunchedEffect(id){
                    vm.onRecipeScreenLoading(id)
                }

                RecipePage(vm = vm)
            }
        }
    }
}