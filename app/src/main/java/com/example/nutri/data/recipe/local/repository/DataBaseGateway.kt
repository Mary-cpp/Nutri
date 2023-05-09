package com.example.nutri.data.recipe.local.repository

import com.example.nutri.domain.recipes.model.Recipe
import kotlinx.coroutines.flow.Flow

interface DataBaseGateway {

    suspend fun saveToLocal(recipe: Recipe, recipeName: String): String
    suspend fun getLocalRecipesList(): List<Recipe>
    suspend fun getRecipe(recipeId: String) : Flow<Recipe>
    suspend fun getRecipesWithNameLike(name: String) : List<Recipe>?
    suspend fun deleteRecipe(recipe: Recipe)
}