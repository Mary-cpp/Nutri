package com.example.nutri.data.recipe.local.repository

import com.example.nutri.domain.recipes.model.Recipe

interface DataBaseGateway {

    suspend fun saveToLocal(recipe: Recipe, recipeName: String): String
    suspend fun getLocalRecipesList(): List<Recipe>
    suspend fun getRecipe(recipeId: String) : Recipe
    suspend fun getRecipesWithNameLike(name: String) : List<Recipe>?
    suspend fun deleteRecipe(recipe: Recipe)
}