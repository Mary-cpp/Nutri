package com.example.nutri.domain.interactor

import com.example.nutri.domain.model.Recipe

interface LocalRecipesInteractor {

    suspend fun saveRecipe(recipe: Recipe , recipeName: String): Int
    suspend fun receiveRecipes(): List<Recipe>
    suspend fun getCommonRecipe(recipeId : Int) : Recipe
}