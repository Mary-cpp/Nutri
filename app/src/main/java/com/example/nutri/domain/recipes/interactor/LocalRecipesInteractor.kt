package com.example.nutri.domain.recipes.interactor

import com.example.nutri.core.ResultState
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.screens.my_recipes.composables.SortAction
import kotlinx.coroutines.flow.Flow

interface LocalRecipesInteractor {

    suspend fun saveRecipe(recipe: Recipe, recipeName: String): String
    suspend fun receiveRecipes(): Flow<ResultState<List<Recipe>>>
    suspend fun getCommonRecipe(recipeId : String) : Flow<ResultState<Recipe>>
    suspend fun deleteRecipe(recipe:Recipe)
    suspend fun getRecipesLike(name: String): List<Recipe>?
    fun sortRecipesBy(sortAction: SortAction, list: List<Recipe>) : Flow<List<Recipe>>
}