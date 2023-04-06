package com.example.nutri.ui.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class NavigationViewModel @Inject constructor(
    val navControllerHolder: NavControllerHolder
) : ViewModel(), MainNavActions {

    override fun navigateToHome() = navControllerHolder.navigateToRoute(Screen.Home.screenRoute)

    fun navigateBack() = navControllerHolder.navigateBack()

    override fun navigateToRecipes() = navControllerHolder.navigateToRoute(Screen.MyRecipes.screenRoute)

    override fun navigateToBmi() = navControllerHolder.navigateToRoute(Screen.BMI.screenRoute)

    override fun navigateToSearch() = navControllerHolder.navigateToRoute(Screen.SearchPage.screenRoute)

    override fun navigateToNewRecipe() = navControllerHolder.navigateToRoute(Screen.CreateRecipe.screenRoute)

    override fun navigateToRecipe(recipeId: String) = navControllerHolder.navigateWithArgs(Screen.Recipe.screenRoute, recipeId)

    override fun navigateToRecipeEditor(recipeId: String) = navControllerHolder.navigateWithArgs(Screen.EditRecipe.screenRoute, recipeId)
}