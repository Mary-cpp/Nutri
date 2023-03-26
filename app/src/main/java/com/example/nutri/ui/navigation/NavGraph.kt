package com.example.nutri.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.recipe.local.repository.DataBaseGatewayImpl
import com.example.nutri.domain.recipes.interactor.LocalRecipeUseCase
import com.example.nutri.ui.screens.RecipeEditPage
import com.example.nutri.ui.screens.bmi.BmiPage
import com.example.nutri.ui.screens.bmi.BmiViewModel
import com.example.nutri.ui.screens.home.HomePage
import com.example.nutri.ui.screens.home.StatisticsViewModel
import com.example.nutri.ui.screens.my_recipes.MyRecipesPage
import com.example.nutri.ui.screens.my_recipes.MyRecipesViewModel
import com.example.nutri.ui.screens.recipe.RecipePage
import com.example.nutri.ui.screens.recipe.RecipeViewModel
import com.example.nutri.ui.screens.search.SearchPage
import com.example.nutri.ui.screens.search.SearchViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues){
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
        composable(
            route = Screen.Home.screenRoute,
            arguments = listOf(navArgument("recipe_id"){
                nullable = true
                type = NavType.StringType})
        ) { backStackEntry ->

            val vm = hiltViewModel<StatisticsViewModel>().apply {
                onStatisticsScreenLoaded()

                val id = backStackEntry.arguments?.getString("recipe_id")
                id?.let {
                    addRecipeToMeal(id)
                }
            }

            HomePage(
                navController = navController,
                vm = vm
            )
        }
        composable(Screen.SearchPage.screenRoute){

            val vm = hiltViewModel<SearchViewModel>()

            SearchPage(
                navController = navController,
                vm = vm
            )
        }
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
        composable(
            route = Screen.Recipe.screenRoute,
            arguments = listOf(navArgument("recipe_id"){
                type = NavType.StringType})
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