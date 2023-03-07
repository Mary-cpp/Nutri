package com.example.nutri.domain.interactor

import com.example.nutri.domain.model.Recipe

interface RecipeInteractor {

    suspend fun analyzeRecipe (param: String): Recipe

    suspend fun saveRecipe(recipe: Recipe , recipeName: String): String
    suspend fun receiveRecipes(): List<Recipe>

    suspend fun deleteMyRecipes() : String
}
