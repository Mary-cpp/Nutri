package com.example.nutri.ui.navigation

interface MainNavActions{

    fun navigateToHome()
    fun navigateToRecipes()
    fun navigateToBmi()
    fun navigateToSearch()
    fun navigateToNewRecipe()
    fun navigateToRecipe(recipeId: String)
    fun navigateToRecipeEditor(recipeId: String)

}