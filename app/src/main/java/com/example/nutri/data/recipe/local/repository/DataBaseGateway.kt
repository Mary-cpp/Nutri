package com.example.nutri.data.recipe.local.repository

import com.example.nutri.domain.recipes.model.Recipe

interface DataBaseGateway {

    suspend fun saveToLocal(recipe: Recipe, recipeName: String): Int
    suspend fun getLocalRecipesList(): List<Recipe>
    suspend fun getRecipe(recipeId: Int) : Recipe
}