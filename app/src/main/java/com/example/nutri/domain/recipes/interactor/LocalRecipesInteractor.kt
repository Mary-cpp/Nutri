package com.example.nutri.domain.recipes.interactor

import com.example.nutri.domain.recipes.model.Recipe

interface LocalRecipesInteractor {

    suspend fun saveRecipe(recipe: Recipe, recipeName: String): String
    suspend fun receiveRecipes(): List<Recipe>
    suspend fun getCommonRecipe(recipeId : String) : Recipe

    suspend fun getRecipesLike(name: String): List<Recipe>?
}