package com.example.nutri.core

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.navigation.Screen
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
    )
= remember(navController, coroutineScope) {
    AppState(navController)
}

@Stable
class AppState(
    val navController: NavHostController
) {

    private val bottomBarRoutes = listOf(Screen.MyRecipes.screenRoute, Screen.Home.screenRoute, Screen.BMI.screenRoute)

    val shouldShowBottomBar: Boolean
    @Composable
    get() = navController.currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    fun navigateToMenuItem(route: String){
        if (navController.currentDestination?.route != route && navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED){
            navController.navigate(route){

                Log.i("navigateToMenuItem", "${navController.currentDestination?.route}     START")

                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.startDestinationId){
                    saveState = true
                }
            }
            Log.i("navigateToMenuItem", "${navController.currentDestination?.route}     END")
        }

    }

    fun navigateUpBackStack(){
        navController.navigateUp()
    }

    fun navigateWithRecipe(route: String, recipe: Recipe){
        if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED){
            navController.navigate("$route/${recipe.id}")
        }
    }
}