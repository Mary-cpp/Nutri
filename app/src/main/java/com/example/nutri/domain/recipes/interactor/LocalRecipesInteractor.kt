package com.example.nutri.domain.recipes.interactor

import com.example.nutri.domain.recipes.model.Recipe
import kotlinx.coroutines.flow.Flow

interface LocalRecipesInteractor {

    suspend fun saveRecipe(recipe: Recipe, recipeName: String): String
    suspend fun receiveRecipes(): List<Recipe>
    suspend fun getCommonRecipe(recipeId : String) : Flow<Recipe>
    suspend fun deleteRecipe(recipe:Recipe)
    suspend fun getRecipesLike(name: String): List<Recipe>?
}