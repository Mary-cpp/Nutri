package com.example.nutri.domain.recipes

import com.example.nutri.core.ResultState
import com.example.nutri.domain.recipes.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeDatabaseGateway {

    suspend fun saveRecipeInfo(recipe: Recipe, recipeName: String): String
    suspend fun getRecipesListFlow(): Flow<ResultState<List<Recipe>>>
    suspend fun getRecipeById(id: String) : Flow<ResultState<Recipe>>
    suspend fun getRecipesWithNameLike(name: String) : List<Recipe>?
    suspend fun deleteRecipe(recipe: Recipe)
}