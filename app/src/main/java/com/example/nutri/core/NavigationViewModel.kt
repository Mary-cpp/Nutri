package com.example.nutri.core

import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import com.example.nutri.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class NavigationViewModel @Inject constructor(
    navControllerProvider: MyNavController
) : ViewModel(), MainNavActions {

    private val navController = navControllerProvider.navController

    private val navOptions = setNavOptions()

    private fun setNavOptions() : NavOptions {

        val optionsBuilder = NavOptions.Builder()
        return optionsBuilder.apply {
            setLaunchSingleTop(true)
            setRestoreState(true)
/*            setPopUpTo(navController!!.graph.startDestinationId,
                inclusive = false,
                saveState = true
            )*/
        }.build()
    }

    override fun navigateToHome() {
        navController!!.navigate(
            route = Screen.Home.screenRoute,
            navOptions = navOptions,
        )
    }

    fun navigateBack(){
        navController!!.navigateUp()
    }

    override fun navigateToRecipes() {
        navController!!.navigate(
            route = Screen.MyRecipes.screenRoute,
            navOptions = navOptions
        )
    }

    override fun navigateToBmi() {
        navController!!.navigate(
            route = Screen.BMI.screenRoute,
            navOptions = navOptions
        )
    }

    override fun navigateToSearch() {
        navController!!.navigate(
            route = Screen.SearchPage.screenRoute,
            navOptions = navOptions
        )
    }

    override fun navigateToNewRecipe() {
        navController!!.navigate(
            route = Screen.CreateRecipe.screenRoute,
            navOptions = navOptions
        )
    }

    override fun navigateToRecipe(recipeId: String) {
        navController!!.navigate(
            route = "${Screen.Recipe.screenRoute}/$recipeId"
        )
    }

    override fun navigateToRecipeEditor(recipeId: String) {
        navController!!.navigate(
            route = "${Screen.EditRecipe.screenRoute}/$recipeId"
        )
    }
}