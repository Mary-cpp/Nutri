package com.example.nutri.core

interface NavActions {

    fun navigateToHome()
    fun navigateToRecipes()
    fun navigateToBmi()
    fun navigateToSearch()
    fun navigateToNewRecipe()
    fun navigateToRecipe(recipeId: String)
    fun navigateToRecipeEditor(recipeId: String)

}